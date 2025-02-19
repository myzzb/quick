package vip.xiaonuo.biz.modular.zskfl.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.biz.modular.dict.entity.BizDict;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.biz.modular.zskfl.entity.ZskFl;
import vip.xiaonuo.biz.modular.zskfl.service.ZskFlService;

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
    @GetMapping("/biz/zskfl/api/one")
    @ApiImplicitParam(name = "type", value = "类型(学习知识库:ZSK_XX 法律法规库:ZSK_FLFG)", required = true, dataType = "String")
    public CommonResult<List<ZskFl>> getOneLevel(String type) {
        return CommonResult.data(zskFlService.getOneLevel(type));
    }

    /**
     * 业务字段事故分类
     * @return
     */
    @Operation(summary = "获取业务字典-API")
    @GetMapping("/biz/zsk/dict")
    @ApiImplicitParam(name = "type", value = "字典类型(" +
            "事故分类:SGFL_TYPE;" +
            "业务分类:ZSK_FLFG;" +
            "事故类型:SGLX_TYPE;" +
            "事故等级:SGDJ_TYPE;" +
            "试题类型:QUESTION_TYPE)", required = true, dataType = "String")
    public CommonResult<List<BizDict>> getDictList(String dictType) {
        return CommonResult.data(zskFlService.getDictList(dictType));
    }

}
