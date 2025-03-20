package com.ocbc.les.common.response;

/**
 * 响应状态码常量
 */
public interface ResultCode {
    /**
     * 操作成功
     */
    int SUCCESS = 200;

    /**
     * 操作失败
     */
    int FAILURE = 500;

    /**
     * 参数错误
     */
    int PARAM_ERROR = 400;

    /**
     * 未授权
     */
    int UNAUTHORIZED = 401;

    /**
     * 禁止访问
     */
    int FORBIDDEN = 403;

    /**
     * 资源不存在
     */
    int NOT_FOUND = 404;

    /**
     * 参数校验失败
     */
    int VALIDATION_FAILED = 422;

    /**
     * 请求频率超限
     */
    int TOO_MANY_REQUESTS = 429;

    /**
     * 服务不可用
     */
    int SERVICE_UNAVAILABLE = 503;

} 