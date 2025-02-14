package vip.xiaonuo.sys.modular.zsk.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.sys.modular.zsk.entity.Zsk;
import vip.xiaonuo.sys.modular.zsk.param.ZskPageParam;
import vip.xiaonuo.sys.modular.zsk.service.ZskService;

/**
 1. @description:  知识库管理控制器-API
 2. @author: zzb
 3. @time: 2025/2/14 
 */
@Tag(name = "知识库管理控制器-API")
@RestController
@Validated
public class ZskApiController {

    @Resource
    private ZskService zskService;

    @Operation(summary = "获取知识库内容列表-API")
    @GetMapping("/sys/zsk/api/page")
    public CommonResult<Page<Zsk>> page(ZskPageParam zskPageParam) {
        return CommonResult.data(zskService.page(zskPageParam));
    }
}
