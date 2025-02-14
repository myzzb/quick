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
package vip.xiaonuo.sys.modular.sgfl.controller;

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
import vip.xiaonuo.sys.modular.sgfl.entity.SgFl;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlAddParam;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlEditParam;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlIdParam;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlPageParam;
import vip.xiaonuo.sys.modular.sgfl.service.SgFlService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 事故分类控制器
 *
 * @author zzb
 * @date  2025/02/12 14:33
 */
@Tag(name = "事故分类控制器")
@RestController
@Validated
public class SgFlController {

    @Resource
    private SgFlService sgFlService;

    /**
     * 获取事故分类分页
     *
     * @author zzb
     * @date  2025/02/12 14:33
     */
    @Operation(summary = "获取事故分类分页")
    @SaCheckPermission("/sys/fl/page")
    @GetMapping("/sys/fl/page")
    public CommonResult<Page<SgFl>> page(SgFlPageParam sgFlPageParam) {
        return CommonResult.data(sgFlService.page(sgFlPageParam));
    }

    /**
     * 添加事故分类
     *
     * @author zzb
     * @date  2025/02/12 14:33
     */
    @Operation(summary = "添加事故分类")
    @CommonLog("添加事故分类")
    @SaCheckPermission("/sys/fl/add")
    @PostMapping("/sys/fl/add")
    public CommonResult<String> add(@RequestBody @Valid SgFlAddParam sgFlAddParam) {
        sgFlService.add(sgFlAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑事故分类
     *
     * @author zzb
     * @date  2025/02/12 14:33
     */
    @Operation(summary = "编辑事故分类")
    @CommonLog("编辑事故分类")
    @SaCheckPermission("/sys/fl/edit")
    @PostMapping("/sys/fl/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgFlEditParam sgFlEditParam) {
        sgFlService.edit(sgFlEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除事故分类
     *
     * @author zzb
     * @date  2025/02/12 14:33
     */
    @Operation(summary = "删除事故分类")
    @CommonLog("删除事故分类")
    @SaCheckPermission("/sys/fl/delete")
    @PostMapping("/sys/fl/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgFlIdParam> sgFlIdParamList) {
        sgFlService.delete(sgFlIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取事故分类详情
     *
     * @author zzb
     * @date  2025/02/12 14:33
     */
    @Operation(summary = "获取事故分类详情")
    @SaCheckPermission("/sys/fl/detail")
    @GetMapping("/sys/fl/detail")
    public CommonResult<SgFl> detail(@Valid SgFlIdParam sgFlIdParam) {
        return CommonResult.data(sgFlService.detail(sgFlIdParam));
    }
}
