package com.github.w4o.ticket.admin.controller;

import com.github.w4o.ticket.admin.domain.CommonResult;
import com.github.w4o.ticket.admin.service.TicketAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author frank
 * @date 2019-05-10
 */
@Api(tags = "后台管理")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TicketAdminService adminService;

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/info")
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        return new CommonResult<>().ok(adminService.getAdminInfo(principal.getName()));
    }
}
