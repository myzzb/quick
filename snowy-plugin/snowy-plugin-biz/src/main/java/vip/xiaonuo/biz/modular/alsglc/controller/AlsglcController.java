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
package vip.xiaonuo.biz.modular.alsglc.controller;

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
import vip.xiaonuo.biz.modular.alsglc.entity.Alsglc;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcAddParam;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcEditParam;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcIdParam;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcPageParam;
import vip.xiaonuo.biz.modular.alsglc.service.AlsglcService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 案例事故流程控制器
 *
 * @author byc
 * @date  2025/01/15 18:31
 */
@Tag(name = "案例事故流程控制器")
@RestController
@Validated
public class AlsglcController {

    @Resource
    private AlsglcService alsglcService;

    /**
     * 获取案例事故流程分页
     *
     * @author byc
     * @date  2025/01/15 18:31
     */
    @Operation(summary = "获取案例事故流程分页")
    @SaCheckPermission("/biz/alsglc/page")
    @GetMapping("/biz/alsglc/page")
    public CommonResult<Page<Alsglc>> page(AlsglcPageParam alsglcPageParam) {
        return CommonResult.data(alsglcService.page(alsglcPageParam));
    }

    /**
     * 添加案例事故流程
     *
     * @author byc
     * @date  2025/01/15 18:31
     */
    @Operation(summary = "添加案例事故流程")
    @CommonLog("添加案例事故流程")
    @SaCheckPermission("/biz/alsglc/add")
    @PostMapping("/biz/alsglc/add")
    public CommonResult<String> add(@RequestBody @Valid AlsglcAddParam alsglcAddParam) {
        alsglcService.add(alsglcAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑案例事故流程
     *
     * @author byc
     * @date  2025/01/15 18:31
     */
    @Operation(summary = "编辑案例事故流程")
    @CommonLog("编辑案例事故流程")
    @SaCheckPermission("/biz/alsglc/edit")
    @PostMapping("/biz/alsglc/edit")
    public CommonResult<String> edit(@RequestBody @Valid AlsglcEditParam alsglcEditParam) {
        alsglcService.edit(alsglcEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除案例事故流程
     *
     * @author byc
     * @date  2025/01/15 18:31
     */
    @Operation(summary = "删除案例事故流程")
    @CommonLog("删除案例事故流程")
    @SaCheckPermission("/biz/alsglc/delete")
    @PostMapping("/biz/alsglc/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<AlsglcIdParam> alsglcIdParamList) {
        alsglcService.delete(alsglcIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取案例事故流程详情
     *
     * @author byc
     * @date  2025/01/15 18:31
     */
    @Operation(summary = "获取案例事故流程详情")
    @SaCheckPermission("/biz/alsglc/detail")
    @GetMapping("/biz/alsglc/detail")
    public CommonResult<Alsglc> detail(@Valid AlsglcIdParam alsglcIdParam) {
        return CommonResult.data(alsglcService.detail(alsglcIdParam));
    }
}
