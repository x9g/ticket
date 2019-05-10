package com.github.w4o.ticket.admin.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author frank
 * @date 2019-05-09 17:11
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Value("${ticket.version}")
    private String version;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        /*
        {
            "timestamp": "2019-05-09T09:37:05.201+0000",
            "status": 400,
            "error": "Bad Request",
            "message": "xxx",
            "path": "/admin/auth/login",
            "transactionId": "39c97f0f-c0fc-4efc-b8a6-b50ff4446eee",
            "version": "0.1"
        }
         */
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("status", HttpStatus.UNAUTHORIZED.value());
        errorAttributes.put("error", "UNAUTHORIZED");
        errorAttributes.put("message", authException.getMessage());
        errorAttributes.put("path", request.getServletPath());
        errorAttributes.put("transactionId", UUID.randomUUID().toString());
        errorAttributes.put("version", version);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        response.getWriter().println(objectMapper.writeValueAsString(errorAttributes));
        response.getWriter().flush();
    }
}
