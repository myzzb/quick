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
package vip.xiaonuo.biz.modular.question.controller;

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
import vip.xiaonuo.biz.modular.question.entity.SgQuestion;
import vip.xiaonuo.biz.modular.question.param.SgQuestionAddParam;
import vip.xiaonuo.biz.modular.question.param.SgQuestionEditParam;
import vip.xiaonuo.biz.modular.question.param.SgQuestionIdParam;
import vip.xiaonuo.biz.modular.question.param.SgQuestionPageParam;
import vip.xiaonuo.biz.modular.question.service.SgQuestionService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 试题控制器
 *
 * @author byc
 * @date  2025/02/07 17:43
 */
@Tag(name = "试题控制器")
@RestController
@Validated
public class SgQuestionController {

    @Resource
    private SgQuestionService sgQuestionService;

    /**
     * 获取试题分页
     *
     * @author byc
     * @date  2025/02/07 17:43
     */
    @Operation(summary = "获取试题分页")
    @SaCheckPermission("/biz/question/page")
    @GetMapping("/biz/question/page")
    public CommonResult<Page<SgQuestion>> page(SgQuestionPageParam sgQuestionPageParam) {
        return CommonResult.data(sgQuestionService.page(sgQuestionPageParam));
    }

    /**
     * 添加试题
     *
     * @author byc
     * @date  2025/02/07 17:43
     */
    @Operation(summary = "添加试题")
    @CommonLog("添加试题")
    @SaCheckPermission("/biz/question/add")
    @PostMapping("/biz/question/add")
    public CommonResult<String> add(@RequestBody @Valid SgQuestionAddParam sgQuestionAddParam) {
        sgQuestionService.add(sgQuestionAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑试题
     *
     * @author byc
     * @date  2025/02/07 17:43
     */
    @Operation(summary = "编辑试题")
    @CommonLog("编辑试题")
    @SaCheckPermission("/biz/question/edit")
    @PostMapping("/biz/question/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgQuestionEditParam sgQuestionEditParam) {
        sgQuestionService.edit(sgQuestionEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除试题
     *
     * @author byc
     * @date  2025/02/07 17:43
     */
    @Operation(summary = "删除试题")
    @CommonLog("删除试题")
    @SaCheckPermission("/biz/question/delete")
    @PostMapping("/biz/question/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgQuestionIdParam> sgQuestionIdParamList) {
        sgQuestionService.delete(sgQuestionIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取试题详情
     *
     * @author byc
     * @date  2025/02/07 17:43
     */
    @Operation(summary = "获取试题详情")
    @SaCheckPermission("/biz/question/detail")
    @GetMapping("/biz/question/detail")
    public CommonResult<SgQuestion> detail(@Valid SgQuestionIdParam sgQuestionIdParam) {
        return CommonResult.data(sgQuestionService.detail(sgQuestionIdParam));
    }
}
