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
package vip.xiaonuo.biz.modular.bqre.controller;

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
import vip.xiaonuo.biz.modular.bqre.entity.SgBqRe;
import vip.xiaonuo.biz.modular.bqre.param.SgBqReAddParam;
import vip.xiaonuo.biz.modular.bqre.param.SgBqReEditParam;
import vip.xiaonuo.biz.modular.bqre.param.SgBqReIdParam;
import vip.xiaonuo.biz.modular.bqre.param.SgBqRePageParam;
import vip.xiaonuo.biz.modular.bqre.service.SgBqReService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 事故标签关联控制器
 *
 * @author zzb
 * @date  2025/02/12 15:01
 */
@Tag(name = "事故标签关联控制器")
@RestController
@Validated
public class SgBqReController {

    @Resource
    private SgBqReService sgBqReService;

    /**
     * 获取事故标签关联分页
     *
     * @author zzb
     * @date  2025/02/12 15:01
     */
    @Operation(summary = "获取事故标签关联分页")
    @SaCheckPermission("/biz/bqre/page")
    @GetMapping("/biz/bqre/page")
    public CommonResult<Page<SgBqRe>> page(SgBqRePageParam sgBqRePageParam) {
        return CommonResult.data(sgBqReService.page(sgBqRePageParam));
    }

    /**
     * 添加事故标签关联
     *
     * @author zzb
     * @date  2025/02/12 15:01
     */
    @Operation(summary = "添加事故标签关联")
    @CommonLog("添加事故标签关联")
    @SaCheckPermission("/biz/bqre/add")
    @PostMapping("/biz/bqre/add")
    public CommonResult<String> add(@RequestBody @Valid SgBqReAddParam sgBqReAddParam) {
        sgBqReService.add(sgBqReAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑事故标签关联
     *
     * @author zzb
     * @date  2025/02/12 15:01
     */
    @Operation(summary = "编辑事故标签关联")
    @CommonLog("编辑事故标签关联")
    @SaCheckPermission("/biz/bqre/edit")
    @PostMapping("/biz/bqre/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgBqReEditParam sgBqReEditParam) {
        sgBqReService.edit(sgBqReEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除事故标签关联
     *
     * @author zzb
     * @date  2025/02/12 15:01
     */
    @Operation(summary = "删除事故标签关联")
    @CommonLog("删除事故标签关联")
    @SaCheckPermission("/biz/bqre/delete")
    @PostMapping("/biz/bqre/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgBqReIdParam> sgBqReIdParamList) {
        sgBqReService.delete(sgBqReIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取事故标签关联详情
     *
     * @author zzb
     * @date  2025/02/12 15:01
     */
    @Operation(summary = "获取事故标签关联详情")
    @SaCheckPermission("/biz/bqre/detail")
    @GetMapping("/biz/bqre/detail")
    public CommonResult<SgBqRe> detail(@Valid SgBqReIdParam sgBqReIdParam) {
        return CommonResult.data(sgBqReService.detail(sgBqReIdParam));
    }
}
