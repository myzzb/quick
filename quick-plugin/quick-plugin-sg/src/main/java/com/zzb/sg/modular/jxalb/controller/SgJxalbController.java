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
package com.zzb.sg.modular.jxalb.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzb.sg.modular.jxalb.entity.SgJxalb;
import com.zzb.sg.modular.jxalb.param.SgJxalbAddParam;
import com.zzb.sg.modular.jxalb.param.SgJxalbEditParam;
import com.zzb.sg.modular.jxalb.param.SgJxalbIdParam;
import com.zzb.sg.modular.jxalb.param.SgJxalbPageParam;
import com.zzb.sg.modular.jxalb.service.SgJxalbService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.zzb.common.annotation.CommonLog;
import com.zzb.common.pojo.CommonResult;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 案例表 控制器
 *
 * @author zzb
 * @date  2025/01/08 16:17
 */
@Tag(name = "案例表 控制器")
@RestController
@Validated
public class SgJxalbController {

    @Resource
    private SgJxalbService sgJxalbService;

    /**
     * 获取案例表 分页
     *
     * @author zzb
     * @date  2025/01/08 16:17
     */
    @Operation(summary = "获取案例表 分页")
    @SaCheckPermission("/sg/jxalb/page")
    @GetMapping("/sg/jxalb/page")
    public CommonResult<Page<SgJxalb>> page(SgJxalbPageParam sgJxalbPageParam) {
        return CommonResult.data(sgJxalbService.page(sgJxalbPageParam));
    }

    /**
     * 添加案例表 
     *
     * @author zzb
     * @date  2025/01/08 16:17
     */
    @Operation(summary = "添加案例表 ")
    @CommonLog("添加案例表 ")
    @SaCheckPermission("/sg/jxalb/add")
    @PostMapping("/sg/jxalb/add")
    public CommonResult<String> add(@RequestBody @Valid SgJxalbAddParam sgJxalbAddParam) {
        sgJxalbService.add(sgJxalbAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑案例表 
     *
     * @author zzb
     * @date  2025/01/08 16:17
     */
    @Operation(summary = "编辑案例表 ")
    @CommonLog("编辑案例表 ")
    @SaCheckPermission("/sg/jxalb/edit")
    @PostMapping("/sg/jxalb/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgJxalbEditParam sgJxalbEditParam) {
        sgJxalbService.edit(sgJxalbEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除案例表 
     *
     * @author zzb
     * @date  2025/01/08 16:17
     */
    @Operation(summary = "删除案例表 ")
    @CommonLog("删除案例表 ")
    @SaCheckPermission("/sg/jxalb/delete")
    @PostMapping("/sg/jxalb/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgJxalbIdParam> sgJxalbIdParamList) {
        sgJxalbService.delete(sgJxalbIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取案例表 详情
     *
     * @author zzb
     * @date  2025/01/08 16:17
     */
    @Operation(summary = "获取案例表 详情")
    @SaCheckPermission("/sg/jxalb/detail")
    @GetMapping("/sg/jxalb/detail")
    public CommonResult<SgJxalb> detail(@Valid SgJxalbIdParam sgJxalbIdParam) {
        return CommonResult.data(sgJxalbService.detail(sgJxalbIdParam));
    }
}
