package com.ocbc.les.common.response;

import com.ocbc.les.common.util.TraceIdUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 * @param <T> 响应数据类型
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 时间戳
     */
    private long timestamp;

    /**
     * 追踪ID
     */
    private String traceId;

    private Result() {
        this.timestamp = System.currentTimeMillis();
        this.traceId = TraceIdUtils.getTraceId();
    }

    /**
     * 成功结果
     * @param <T> 数据类型
     * @return 成功的结果
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 成功结果（带数据）
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 成功的结果
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    /**
     * 成功结果（带消息和数据）
     * @param message 消息
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 成功的结果
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message)
                .setData(data)
                .setSuccess(true);
    }

    /**
     * 失败结果
     * @param <T> 数据类型
     * @return 失败的结果
     */
    public static <T> Result<T> fail() {
        return new Result<T>()
                .setCode(ResultCode.FAILURE)
                .setMessage("操作失败")
                .setSuccess(false);
    }

    /**
     * 失败结果（带消息）
     * @param message 消息
     * @param <T> 数据类型
     * @return 失败的结果
     */
    public static <T> Result<T> fail(String message) {
        return fail(500, message);
    }

    /**
     * 失败结果（带状态码和消息）
     * @param code 状态码
     * @param message 消息
     * @param <T> 数据类型
     * @return 失败的结果
     */
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

    /**
     * 设置追踪ID
     * @param traceId 追踪ID
     * @return 结果对象
     */
    public Result<T> setTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }
} 