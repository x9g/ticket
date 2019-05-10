package com.github.w4o.ticket.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.UUID;

/**
 * @author frank
 * @date 2019-05-09 11:42
 */
@Component
@Slf4j
public class MyCustomErrorAttributes extends DefaultErrorAttributes {

    @Value("${ticket.version}")
    private String version;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        String uuid = UUID.randomUUID().toString();
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("transactionId", uuid);
        errorAttributes.put("version", version);

        Throwable throwable = getError(webRequest);
        if (throwable != null) {
            if (throwable instanceof HttpMessageNotReadableException) {
                errorAttributes.put("message", "非法请求");
            } else {
                log.error(uuid, throwable);
            }
        }


        return errorAttributes;
    }
}
