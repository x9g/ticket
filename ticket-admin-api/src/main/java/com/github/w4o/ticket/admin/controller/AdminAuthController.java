package com.github.w4o.ticket.admin.controller;

import com.github.w4o.ticket.admin.domain.CommonResult;
import com.github.w4o.ticket.admin.domain.dto.AdminLoginParam;
import com.github.w4o.ticket.admin.domain.dto.AdminLoginResult;
import com.github.w4o.ticket.admin.service.TicketAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Admin 授权
 *
 * @author frank
 * @date 2019-05-09 11:35
 */
@RestController
@RequestMapping(value = "/admin/auth")
@Api(tags = "后台授权")
@Validated
@Slf4j
public class AdminAuthController {

    @Autowired
    private TicketAdminService adminService;

    /**
     * Admin 登陆
     */
    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    @ResponseBody
    public CommonResult login(@RequestBody AdminLoginParam adminLoginParam) {
        AdminLoginResult result = adminService.login(adminLoginParam);
        return new CommonResult<AdminLoginResult>().ok(result);
    }

    /**
     * Admin 登出
     */
    @ApiOperation(value = "登出功能")
    @PostMapping("/logout")
    @ResponseBody
    public CommonResult logout() {
        return new CommonResult<>().ok(null);
    }
}
