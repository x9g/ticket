package com.github.w4o.ticket.admin.domain.bo;

import com.github.w4o.ticket.db.model.TicketAdmin;
import com.github.w4o.ticket.db.model.TicketPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author frank
 * @date 2019-05-09 16:03
 */
public class AdminUserDetails implements UserDetails {

    private TicketAdmin admin;
    private List<TicketPermission> permissionList;

    public AdminUserDetails(TicketAdmin admin, List<TicketPermission> permissionList) {
        this.admin = admin;
        this.permissionList = permissionList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getPermission() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return admin.getStatus().equals(1);
    }

}
