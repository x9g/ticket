package com.github.w4o.ticket.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author frank
 * @date 2019-05-09 16:14
 */
@Data
@AllArgsConstructor
public class AdminLoginResult {

    private String token;
    private String tokenHead;
}
