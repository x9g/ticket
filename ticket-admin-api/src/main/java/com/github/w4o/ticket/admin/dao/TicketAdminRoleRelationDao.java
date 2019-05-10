package com.github.w4o.ticket.admin.dao;

import com.github.w4o.ticket.db.model.TicketPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author frank
 * @date 2019-05-09 15:59
 */
public interface TicketAdminRoleRelationDao {

    /**
     * 获取管理员所有权限
     *
     * @param adminId 管理员Id
     * @return 权限集合
     */
    List<TicketPermission> getPermissionList(@Param("adminId") Long adminId);
}
