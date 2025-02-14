package vip.xiaonuo.sys.modular.zskfl.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.sys.modular.zskfl.entity.ZskFl;
import vip.xiaonuo.sys.modular.zskfl.service.ZskFlService;

import java.util.List;

/**
 1. @description:  知识库分类控制器-API
 2. @author: zzb
 3. @time: 2025/2/14 
 */
@Tag(name = "知识库分类控制器-API")
@RestController
@Validated
public class ZskFlApiController {

    @Resource
    private ZskFlService zskFlService;


    @Operation(summary = "获取知识库一级分类-API")
    @GetMapping("/sys/zskfl/api/one")
    @ApiImplicitParam(name = "type", value = "类型(学习知识库:ZSK_XX 法律法规库:ZSK_FLFG)", required = true, dataType = "String")
    public CommonResult<List<ZskFl>> getOneLevel(String type) {
        return CommonResult.data(zskFlService.getOneLevel(type));
    }

}
