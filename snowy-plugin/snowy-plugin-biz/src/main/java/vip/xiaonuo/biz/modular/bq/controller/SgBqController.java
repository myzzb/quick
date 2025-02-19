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
package vip.xiaonuo.biz.modular.bq.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.biz.modular.bq.param.SgBqEditParam;
import vip.xiaonuo.biz.modular.bq.param.SgBqIdParam;
import vip.xiaonuo.biz.modular.bq.param.SgBqPageParam;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.biz.modular.bq.entity.SgBq;
import vip.xiaonuo.biz.modular.bq.param.SgBqAddParam;
import vip.xiaonuo.biz.modular.bq.service.SgBqService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 标签管理控制器
 *
 * @author zzb
 * @date  2025/02/12 11:55
 */
@Tag(name = "标签管理控制器")
@RestController
@Validated
public class SgBqController {

    @Resource
    private SgBqService sgBqService;

    /**
     * 获取标签管理分页
     *
     * @author zzb
     * @date  2025/02/12 11:55
     */
    @Operation(summary = "获取标签管理分页")
    @SaCheckPermission("/biz/bq/page")
    @GetMapping("/biz/bq/page")
    public CommonResult<Page<SgBq>> page(SgBqPageParam sgBqPageParam) {
        return CommonResult.data(sgBqService.page(sgBqPageParam));
    }

    /**
     * 添加标签管理
     *
     * @author zzb
     * @date  2025/02/12 11:55
     */
    @Operation(summary = "添加标签管理")
    @CommonLog("添加标签管理")
    @SaCheckPermission("/biz/bq/add")
    @PostMapping("/biz/bq/add")
    public CommonResult<String> add(@RequestBody @Valid SgBqAddParam sgBqAddParam) {
        sgBqService.add(sgBqAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑标签管理
     *
     * @author zzb
     * @date  2025/02/12 11:55
     */
    @Operation(summary = "编辑标签管理")
    @CommonLog("编辑标签管理")
    @SaCheckPermission("/biz/bq/edit")
    @PostMapping("/biz/bq/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgBqEditParam sgBqEditParam) {
        sgBqService.edit(sgBqEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除标签管理
     *
     * @author zzb
     * @date  2025/02/12 11:55
     */
    @Operation(summary = "删除标签管理")
    @CommonLog("删除标签管理")
    @SaCheckPermission("/biz/bq/delete")
    @PostMapping("/biz/bq/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgBqIdParam> sgBqIdParamList) {
        sgBqService.delete(sgBqIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取标签管理详情
     *
     * @author zzb
     * @date  2025/02/12 11:55
     */
    @Operation(summary = "获取标签管理详情")
    @SaCheckPermission("/biz/bq/detail")
    @GetMapping("/biz/bq/detail")
    public CommonResult<SgBq> detail(@Valid SgBqIdParam sgBqIdParam) {
        return CommonResult.data(sgBqService.detail(sgBqIdParam));
    }
}
