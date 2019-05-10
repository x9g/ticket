package com.github.w4o.ticket.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @author frank
 * @date 2019-05-09 11:22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class CommonResult<T> {

    @ApiModelProperty(value = "状态码，0为成功")
    private Integer status;
    @ApiModelProperty(value = "响应信息")
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'.'sss'+'000")
    private Date timestamp;
    private String version;
    @ApiModelProperty(value = "接口返回有效数据")
    private T data;
    private String transactionId;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public CommonResult ok(T data) {
        this.status = HttpStatus.OK.value();
        this.message = "success";
        this.data = data;
        this.timestamp = new Date();
        return this;
    }

}
