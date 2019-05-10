package com.github.w4o.ticket.admin.domain.dto;

import lombok.Data;

/**
 * @author frank
 * @date 2019-05-10
 */
@Data
public class AdminInfoResult {

    private String username;
    private String[] roles;
    private String icon;
}
