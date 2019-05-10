package com.github.w4o.ticket.admin.service;

import com.github.w4o.ticket.admin.domain.dto.AdminInfoResult;
import com.github.w4o.ticket.admin.domain.dto.AdminLoginParam;
import com.github.w4o.ticket.admin.domain.dto.AdminLoginResult;
import com.github.w4o.ticket.db.model.TicketAdmin;
import com.github.w4o.ticket.db.model.TicketPermission;

import java.util.List;

/**
 * @author frank
 * @date 2019-05-09 09:58
 */
public interface TicketAdminService {

    /**
     * 根据用户名查询admin
     *
     * @param username 用户名
     * @return admin
     */
    TicketAdmin getAdminByUsername(String username);

    /**
     * 根据管理员ID查询Permission集合
     *
     * @param adminId 管理员ID
     * @return 权限集合
     */
    List<TicketPermission> getPermissionList(Long adminId);

    /**
     * 管理员登陆
     *
     * @param loginParam 登陆参数，包含username和password
     * @return 登陆结果，包含token和tokenHead
     */
    AdminLoginResult login(AdminLoginParam loginParam);

    /**
     * 根据用户名获取管理员信息
     *
     * @param username 用户名
     * @return 管理信息
     */
    AdminInfoResult getAdminInfo(String username);

}
