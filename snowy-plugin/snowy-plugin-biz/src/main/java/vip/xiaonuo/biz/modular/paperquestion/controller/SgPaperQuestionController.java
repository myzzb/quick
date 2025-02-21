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
package vip.xiaonuo.biz.modular.paperquestion.controller;

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
import vip.xiaonuo.biz.modular.paperquestion.entity.SgPaperQuestion;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionAddParam;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionEditParam;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionIdParam;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionPageParam;
import vip.xiaonuo.biz.modular.paperquestion.service.SgPaperQuestionService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 试卷试题关系表控制器
 *
 * @author byc
 * @date  2025/02/21 11:35
 */
@Tag(name = "试卷试题关系表控制器")
@RestController
@Validated
public class SgPaperQuestionController {

    @Resource
    private SgPaperQuestionService sgPaperQuestionService;

    /**
     * 获取试卷试题关系表分页
     *
     * @author byc
     * @date  2025/02/21 11:35
     */
    @Operation(summary = "获取试卷试题关系表分页")
    @SaCheckPermission("/biz/paperquestion/page")
    @GetMapping("/biz/paperquestion/page")
    public CommonResult<Page<SgPaperQuestion>> page(SgPaperQuestionPageParam sgPaperQuestionPageParam) {
        return CommonResult.data(sgPaperQuestionService.page(sgPaperQuestionPageParam));
    }

    /**
     * 添加试卷试题关系表
     *
     * @author byc
     * @date  2025/02/21 11:35
     */
    @Operation(summary = "添加试卷试题关系表")
    @CommonLog("添加试卷试题关系表")
    @SaCheckPermission("/biz/paperquestion/add")
    @PostMapping("/biz/paperquestion/add")
    public CommonResult<String> add(@RequestBody @Valid SgPaperQuestionAddParam sgPaperQuestionAddParam) {
        sgPaperQuestionService.add(sgPaperQuestionAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑试卷试题关系表
     *
     * @author byc
     * @date  2025/02/21 11:35
     */
    @Operation(summary = "编辑试卷试题关系表")
    @CommonLog("编辑试卷试题关系表")
    @SaCheckPermission("/biz/paperquestion/edit")
    @PostMapping("/biz/paperquestion/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgPaperQuestionEditParam sgPaperQuestionEditParam) {
        sgPaperQuestionService.edit(sgPaperQuestionEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除试卷试题关系表
     *
     * @author byc
     * @date  2025/02/21 11:35
     */
    @Operation(summary = "删除试卷试题关系表")
    @CommonLog("删除试卷试题关系表")
    @SaCheckPermission("/biz/paperquestion/delete")
    @PostMapping("/biz/paperquestion/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgPaperQuestionIdParam> sgPaperQuestionIdParamList) {
        sgPaperQuestionService.delete(sgPaperQuestionIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取试卷试题关系表详情
     *
     * @author byc
     * @date  2025/02/21 11:35
     */
    @Operation(summary = "获取试卷试题关系表详情")
    @SaCheckPermission("/biz/paperquestion/detail")
    @GetMapping("/biz/paperquestion/detail")
    public CommonResult<SgPaperQuestion> detail(@Valid SgPaperQuestionIdParam sgPaperQuestionIdParam) {
        return CommonResult.data(sgPaperQuestionService.detail(sgPaperQuestionIdParam));
    }
}
