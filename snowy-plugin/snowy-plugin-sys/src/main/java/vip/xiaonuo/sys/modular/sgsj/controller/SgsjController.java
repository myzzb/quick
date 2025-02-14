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
package vip.xiaonuo.sys.modular.sgsj.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.sys.modular.sgsj.entity.Sgsj;
import vip.xiaonuo.sys.modular.sgsj.param.SgsjAddParam;
import vip.xiaonuo.sys.modular.sgsj.param.SgsjEditParam;
import vip.xiaonuo.sys.modular.sgsj.param.SgsjIdParam;
import vip.xiaonuo.sys.modular.sgsj.param.SgsjPageParam;
import vip.xiaonuo.sys.modular.sgsj.service.SgsjService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 事故数据信息表控制器
 *
 * @author zzb
 * @date  2025/02/12 10:16
 */
@Tag(name = "事故数据信息表控制器")
@RestController
@Validated
public class SgsjController {

    @Resource
    private SgsjService sgsjService;

    /**
     * 获取事故数据信息表分页
     *
     * @author zzb
     * @date  2025/02/12 10:16
     */
    @Operation(summary = "获取事故数据信息表分页")
    @SaCheckPermission("/sys/sgsj/page")
    @GetMapping("/sys/sgsj/page")
    public CommonResult<Page<Sgsj>> page(SgsjPageParam sgsjPageParam) {
        return CommonResult.data(sgsjService.page(sgsjPageParam));
    }

    /**
     * 添加事故数据信息表
     *
     * @author zzb
     * @date  2025/02/12 10:16
     */
    @Operation(summary = "添加事故数据信息表")
    @CommonLog("添加事故数据信息表")
    @SaCheckPermission("/sys/sgsj/add")
    @PostMapping("/sys/sgsj/add")
    public CommonResult<String> add(@RequestBody @Valid SgsjAddParam sgsjAddParam) {
        sgsjService.add(sgsjAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑事故数据信息表
     *
     * @author zzb
     * @date  2025/02/12 10:16
     */
    @Operation(summary = "编辑事故数据信息表")
    @CommonLog("编辑事故数据信息表")
    @SaCheckPermission("/sys/sgsj/edit")
    @PostMapping("/sys/sgsj/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgsjEditParam sgsjEditParam) {
        sgsjService.edit(sgsjEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除事故数据信息表
     *
     * @author zzb
     * @date  2025/02/12 10:16
     */
    @Operation(summary = "删除事故数据信息表")
    @CommonLog("删除事故数据信息表")
    @SaCheckPermission("/sys/sgsj/delete")
    @PostMapping("/sys/sgsj/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgsjIdParam> sgsjIdParamList) {
        sgsjService.delete(sgsjIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取事故数据信息表详情
     *
     * @author zzb
     * @date  2025/02/12 10:16
     */
    @Operation(summary = "获取事故数据信息表详情")
    @SaCheckPermission("/sys/sgsj/detail")
    @GetMapping("/sys/sgsj/detail")
    public CommonResult<Sgsj> detail(@Valid SgsjIdParam sgsjIdParam) {
        return CommonResult.data(sgsjService.detail(sgsjIdParam));
    }
}
