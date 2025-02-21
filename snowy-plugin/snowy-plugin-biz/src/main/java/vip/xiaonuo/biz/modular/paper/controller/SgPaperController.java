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
package vip.xiaonuo.biz.modular.paper.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionAddParam;
import vip.xiaonuo.biz.modular.question.entity.SgQuestion;
import vip.xiaonuo.biz.modular.question.service.SgQuestionService;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.biz.modular.paper.entity.SgPaper;
import vip.xiaonuo.biz.modular.paper.param.SgPaperAddParam;
import vip.xiaonuo.biz.modular.paper.param.SgPaperEditParam;
import vip.xiaonuo.biz.modular.paper.param.SgPaperIdParam;
import vip.xiaonuo.biz.modular.paper.param.SgPaperPageParam;
import vip.xiaonuo.biz.modular.paper.service.SgPaperService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 试卷表控制器
 *
 * @author byc
 * @date  2025/02/07 15:58
 */
@Tag(name = "试卷表控制器")
@RestController
@Validated
public class SgPaperController {

    @Resource
    private SgPaperService sgPaperService;

    @Resource
    private SgQuestionService sgQuestionService;

    /**
     * 获取试卷表分页
     *
     * @author byc
     * @date  2025/02/07 15:58
     */
    @Operation(summary = "获取试卷表分页")
    @SaCheckPermission("/biz/paper/page")
    @GetMapping("/biz/paper/page")
    public CommonResult<Page<SgPaper>> page(SgPaperPageParam sgPaperPageParam) {
        return CommonResult.data(sgPaperService.page(sgPaperPageParam));
    }

    @Operation(summary = "获取试卷表列表")
//    @SaCheckPermission("/biz/paper/list")
    @PostMapping("/biz/paper/list")
    public CommonResult<List<SgPaper>> list() {
        return CommonResult.data(sgPaperService.allList());
    }

    /**
     * 添加试卷表
     *
     * @author byc
     * @date  2025/02/07 15:58
     */
    @Operation(summary = "添加试卷表")
    @CommonLog("添加试卷表")
    @SaCheckPermission("/biz/paper/add")
    @PostMapping("/biz/paper/add")
    public CommonResult<String> add(@RequestBody @Valid SgPaperAddParam sgPaperAddParam) {
        sgPaperService.add(sgPaperAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑试卷表
     *
     * @author byc
     * @date  2025/02/07 15:58
     */
    @Operation(summary = "编辑试卷表")
    @CommonLog("编辑试卷表")
    @SaCheckPermission("/biz/paper/edit")
    @PostMapping("/biz/paper/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgPaperEditParam sgPaperEditParam) {
        sgPaperService.edit(sgPaperEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除试卷表
     *
     * @author byc
     * @date  2025/02/07 15:58
     */
    @Operation(summary = "删除试卷表")
    @CommonLog("删除试卷表")
    @SaCheckPermission("/biz/paper/delete")
    @PostMapping("/biz/paper/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgPaperIdParam> sgPaperIdParamList) {
        sgPaperService.delete(sgPaperIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取试卷表详情
     *
     * @author byc
     * @date  2025/02/07 15:58
     */
    @Operation(summary = "获取试卷表详情")
    @SaCheckPermission("/biz/paper/detail")
    @GetMapping("/biz/paper/detail")
    public CommonResult<SgPaper> detail(@Valid SgPaperIdParam sgPaperIdParam) {
        return CommonResult.data(sgPaperService.detail(sgPaperIdParam));
    }

    /**
     * 获取试卷关联的试题列表
     */
    @Operation(summary = "查看试题")
    @SaCheckPermission("/biz/paper/questions")
    @GetMapping("/biz/paper/questions")
    public CommonResult<List<SgQuestion>> question(@Valid SgPaperIdParam sgPaperIdParam) {
        return CommonResult.data(sgQuestionService.question(sgPaperIdParam));
    }

    //addQuestions
    @Operation(summary = "添加试题")
    @CommonLog("作业管理-添加试题")
    @SaCheckPermission("/biz/paper/addQuestions")
    @PostMapping("/biz/paper/addQuestions")
    public CommonResult<String> addQuestions(@RequestBody @Valid List<SgPaperQuestionAddParam> sgPaperQuestionAddParams) {
        sgPaperService.addQuestions(sgPaperQuestionAddParams);
        return CommonResult.ok();
    }
}
