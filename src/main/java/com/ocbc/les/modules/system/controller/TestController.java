package com.ocbc.les.modules.system.controller;

import com.ocbc.les.common.response.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 */
@Tag(name = "测试接口", description = "用于系统测试的相关接口")
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试接口
     *
     * @return 测试结果
     */
    @Operation(summary = "测试接口", description = "用于测试系统是否正常运行")
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Hello, OCBC Risk Management System!");
    }
}