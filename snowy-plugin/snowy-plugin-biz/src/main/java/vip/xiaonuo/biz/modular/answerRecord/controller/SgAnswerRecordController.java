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
package vip.xiaonuo.biz.modular.answerRecord.controller;

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
import vip.xiaonuo.biz.modular.answerRecord.entity.SgAnswerRecord;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordAddParam;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordEditParam;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordIdParam;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordPageParam;
import vip.xiaonuo.biz.modular.answerRecord.service.SgAnswerRecordService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 答题记录控制器
 *
 * @author byc
 * @date  2025/02/08 15:06
 */
@Tag(name = "答题记录控制器")
@RestController
@Validated
public class SgAnswerRecordController {

    @Resource
    private SgAnswerRecordService sgAnswerRecordService;

    /**
     * 获取答题记录分页
     *
     * @author byc
     * @date  2025/02/08 15:06
     */
    @Operation(summary = "获取答题记录分页")
    @SaCheckPermission("/biz/answerRecord/page")
    @GetMapping("/biz/answerRecord/page")
    public CommonResult<Page<SgAnswerRecord>> page(SgAnswerRecordPageParam sgAnswerRecordPageParam) {
        return CommonResult.data(sgAnswerRecordService.page(sgAnswerRecordPageParam));
    }

    /**
     * 添加答题记录
     *
     * @author byc
     * @date  2025/02/08 15:06
     */
    @Operation(summary = "添加答题记录")
    @CommonLog("添加答题记录")
    @SaCheckPermission("/biz/answerRecord/add")
    @PostMapping("/biz/answerRecord/add")
    public CommonResult<String> add(@RequestBody @Valid SgAnswerRecordAddParam sgAnswerRecordAddParam) {
        sgAnswerRecordService.add(sgAnswerRecordAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑答题记录
     *
     * @author byc
     * @date  2025/02/08 15:06
     */
    @Operation(summary = "编辑答题记录")
    @CommonLog("编辑答题记录")
    @SaCheckPermission("/biz/answerRecord/edit")
    @PostMapping("/biz/answerRecord/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgAnswerRecordEditParam sgAnswerRecordEditParam) {
        sgAnswerRecordService.edit(sgAnswerRecordEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除答题记录
     *
     * @author byc
     * @date  2025/02/08 15:06
     */
    @Operation(summary = "删除答题记录")
    @CommonLog("删除答题记录")
    @SaCheckPermission("/biz/answerRecord/delete")
    @PostMapping("/biz/answerRecord/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgAnswerRecordIdParam> sgAnswerRecordIdParamList) {
        sgAnswerRecordService.delete(sgAnswerRecordIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取答题记录详情
     *
     * @author byc
     * @date  2025/02/08 15:06
     */
    @Operation(summary = "获取答题记录详情")
    @SaCheckPermission("/biz/answerRecord/detail")
    @GetMapping("/biz/answerRecord/detail")
    public CommonResult<SgAnswerRecord> detail(@Valid SgAnswerRecordIdParam sgAnswerRecordIdParam) {
        return CommonResult.data(sgAnswerRecordService.detail(sgAnswerRecordIdParam));
    }

    @Operation(summary = "提交试卷")
    @CommonLog("添加答题记录")
//    @SaCheckPermission("/biz/answerRecord/add")
    @PostMapping("/biz/answerRecord/submit")
    public CommonResult<Integer> submitAnswer(@RequestBody @Valid List<SgAnswerRecordAddParam> answerRecordAddParamList) {
        return CommonResult.data(sgAnswerRecordService.submitAnswer(answerRecordAddParamList));
    }
}
