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
package vip.xiaonuo.biz.modular.ywjx.controller;

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
import vip.xiaonuo.biz.modular.ywjx.entity.SgYwjx;
import vip.xiaonuo.biz.modular.ywjx.param.SgYwjxAddParam;
import vip.xiaonuo.biz.modular.ywjx.param.SgYwjxEditParam;
import vip.xiaonuo.biz.modular.ywjx.param.SgYwjxIdParam;
import vip.xiaonuo.biz.modular.ywjx.param.SgYwjxPageParam;
import vip.xiaonuo.biz.modular.ywjx.service.SgYwjxService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 业务教学控制器
 *
 * @author byc
 * @date  2025/01/22 15:09
 */
@Tag(name = "业务教学控制器")
@RestController
@Validated
public class SgYwjxController {

    @Resource
    private SgYwjxService sgYwjxService;

    /**
     * 获取业务教学分页
     *
     * @author byc
     * @date  2025/01/22 15:09
     */
    @Operation(summary = "获取业务教学分页")
    @SaCheckPermission("/biz/ywjx/page")
    @GetMapping("/biz/ywjx/page")
    public CommonResult<Page<SgYwjx>> page(SgYwjxPageParam sgYwjxPageParam) {
        return CommonResult.data(sgYwjxService.page(sgYwjxPageParam));
    }

    /**
     * 添加业务教学
     *
     * @author byc
     * @date  2025/01/22 15:09
     */
    @Operation(summary = "添加业务教学")
    @CommonLog("添加业务教学")
    @SaCheckPermission("/biz/ywjx/add")
    @PostMapping("/biz/ywjx/add")
    public CommonResult<String> add(@RequestBody @Valid SgYwjxAddParam sgYwjxAddParam) {
        sgYwjxService.add(sgYwjxAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑业务教学
     *
     * @author byc
     * @date  2025/01/22 15:09
     */
    @Operation(summary = "编辑业务教学")
    @CommonLog("编辑业务教学")
    @SaCheckPermission("/biz/ywjx/edit")
    @PostMapping("/biz/ywjx/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgYwjxEditParam sgYwjxEditParam) {
        sgYwjxService.edit(sgYwjxEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除业务教学
     *
     * @author byc
     * @date  2025/01/22 15:09
     */
    @Operation(summary = "删除业务教学")
    @CommonLog("删除业务教学")
    @SaCheckPermission("/biz/ywjx/delete")
    @PostMapping("/biz/ywjx/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgYwjxIdParam> sgYwjxIdParamList) {
        sgYwjxService.delete(sgYwjxIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取业务教学详情
     *
     * @author byc
     * @date  2025/01/22 15:09
     */
    @Operation(summary = "获取业务教学详情")
    @SaCheckPermission("/biz/ywjx/detail")
    @GetMapping("/biz/ywjx/detail")
    public CommonResult<SgYwjx> detail(@Valid SgYwjxIdParam sgYwjxIdParam) {
        return CommonResult.data(sgYwjxService.detail(sgYwjxIdParam));
    }
}
