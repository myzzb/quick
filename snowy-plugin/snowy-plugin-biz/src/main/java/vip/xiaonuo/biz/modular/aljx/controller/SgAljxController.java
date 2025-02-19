/*
 * Copyright [2022] [https://www.xiaonuo.vip]
 *
 * Snowy采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：
 *
 * 1.请不要删除和修改根目录下的LICENSE文件。
 * 2.请不要删除和修改Snowy源码头部的版权声明。
 * 3.本项目代码可免费商业使用，商业使用请保留源码和相关描述文件的项目出处，作者声明等。
 * 4.分发源码时候，请注明软件出处 https://www.xiaonuo.vip
 * 5.不可二次分发开源参与同类竞品，如有想法可联系团队xiaonuobase@qq.com商议合作。
 * 6.若您的项目无法满足以上几点，需要更多功能代码，获取Snowy商业授权许可，请在官网购买授权，地址为 https://www.xiaonuo.vip
 */
package vip.xiaonuo.biz.modular.aljx.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.biz.modular.sgsj.entity.Sgsj;
import vip.xiaonuo.biz.modular.sgsj.param.SgsjAddParam;
import vip.xiaonuo.biz.modular.sgsj.param.SgsjEditParam;
import vip.xiaonuo.biz.modular.sgsj.param.SgsjIdParam;
import vip.xiaonuo.biz.modular.sgsj.param.SgsjPageParam;
import vip.xiaonuo.biz.modular.sgsj.service.SgsjService;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.biz.modular.aljx.entity.SgAljx;
import vip.xiaonuo.biz.modular.aljx.param.SgAljxAddParam;
import vip.xiaonuo.biz.modular.aljx.param.SgAljxEditParam;
import vip.xiaonuo.biz.modular.aljx.param.SgAljxIdParam;
import vip.xiaonuo.biz.modular.aljx.param.SgAljxPageParam;
import vip.xiaonuo.biz.modular.aljx.service.SgAljxService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 案例教学控制器
 *
 * @author byc
 * @date  2025/01/09 17:49
 */
@Tag(name = "案例教学控制器")
@RestController
@Validated
public class SgAljxController {

    @Resource
    private SgAljxService sgAljxService;

    @Resource
    private SgsjService sgsjService;

    /**
     * 获取案例教学分页
     *
     * @author byc
     * @date  2025/01/09 17:49
     */
    @Operation(summary = "获取案例教学分页")
    @SaCheckPermission("/biz/aljx/page")
    @GetMapping("/biz/aljx/page")
    public CommonResult<Page<Sgsj>> page(SgAljxPageParam sgAljxPageParam) {
        SgsjPageParam sgsjPageParam = new SgsjPageParam();
        BeanUtils.copyProperties(sgAljxPageParam, sgsjPageParam);
        sgsjPageParam.setIsAljx("TRUE");
        return CommonResult.data(sgsjService.page(sgsjPageParam));
    }

    /**
     * 添加案例教学
     *
     * @author byc
     * @date  2025/01/09 17:49
     */
    @Operation(summary = "添加案例教学")
    @CommonLog("添加案例教学")
    @SaCheckPermission("/biz/aljx/add")
    @PostMapping("/biz/aljx/add")
    public CommonResult<String> add(@RequestBody @Valid SgsjAddParam sgsjAddParam) {
        sgsjAddParam.setIsAljx("TRUE");
        sgsjService.add(sgsjAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑案例教学
     *
     * @author byc
     * @date  2025/01/09 17:49
     */
    @Operation(summary = "编辑案例教学")
    @CommonLog("编辑案例教学")
    @SaCheckPermission("/biz/aljx/edit")
    @PostMapping("/biz/aljx/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgsjEditParam sgsjEditParam) {
        sgsjService.edit(sgsjEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除案例教学
     *
     * @author byc
     * @date  2025/01/09 17:49
     */
    @Operation(summary = "删除案例教学")
    @CommonLog("删除案例教学")
    @SaCheckPermission("/biz/aljx/delete")
    @PostMapping("/biz/aljx/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                               List<SgsjIdParam> sgsjIdParamList) {
        sgsjService.delete(sgsjIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取案例教学详情
     *
     * @author byc
     * @date  2025/01/09 17:49
     */
    @Operation(summary = "获取案例教学详情")
    @SaCheckPermission("/biz/aljx/detail")
    @GetMapping("/biz/aljx/detail")
    public CommonResult<Sgsj> detail(@Valid SgsjIdParam sgsjIdParam) {
        return CommonResult.data(sgsjService.detail(sgsjIdParam));
    }
}
