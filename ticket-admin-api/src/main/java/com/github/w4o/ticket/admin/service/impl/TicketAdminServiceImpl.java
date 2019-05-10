package com.github.w4o.ticket.admin.service.impl;

import com.github.w4o.ticket.admin.component.JwtTokenUtil;
import com.github.w4o.ticket.admin.dao.TicketAdminRoleRelationDao;
import com.github.w4o.ticket.admin.domain.dto.AdminInfoResult;
import com.github.w4o.ticket.admin.domain.dto.AdminLoginParam;
import com.github.w4o.ticket.admin.domain.dto.AdminLoginResult;
import com.github.w4o.ticket.admin.service.TicketAdminService;
import com.github.w4o.ticket.db.mapper.TicketAdminLoginLogMapper;
import com.github.w4o.ticket.db.mapper.TicketAdminMapper;
import com.github.w4o.ticket.db.model.TicketAdmin;
import com.github.w4o.ticket.db.model.TicketAdminExample;
import com.github.w4o.ticket.db.model.TicketAdminLoginLog;
import com.github.w4o.ticket.db.model.TicketPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author frank
 * @date 2019-05-09 09:58
 */
@Service
@Slf4j
public class TicketAdminServiceImpl implements TicketAdminService {

    @Autowired
    private TicketAdminMapper adminMapper;
    @Autowired
    private TicketAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TicketAdminLoginLogMapper loginLogMapper;


    @Value("${ticket.jwt.token.head}")
    private String tokenHead;

    @Override
    public TicketAdmin getAdminByUsername(String username) {
        TicketAdminExample example = new TicketAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<TicketAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public List<TicketPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

    @Override
    public AdminLoginResult login(AdminLoginParam loginParam) {
        //密码需要客户端加密后传递
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (!passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        updateLoginTimeByUsername(loginParam.getUsername());
        insertLoginLog(loginParam.getUsername());

        return new AdminLoginResult(token, tokenHead);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        TicketAdmin admin = new TicketAdmin();
        admin.setLoginTime(new Date());
        TicketAdminExample example = new TicketAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminMapper.updateByExampleSelective(admin, example);
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        TicketAdmin admin = getAdminByUsername(username);
        TicketAdminLoginLog loginLog = new TicketAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException();
        }
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }

    @Override
    public AdminInfoResult getAdminInfo(String username) {
        TicketAdmin admin = this.getAdminByUsername(username);
        AdminInfoResult result = new AdminInfoResult();
        result.setUsername(username);
        result.setIcon(admin.getIcon());
        result.setRoles(new String[]{"TEST"});
        return result;
    }
}
