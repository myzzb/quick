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
package vip.xiaonuo.biz.modular.answerStatistics.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.biz.modular.answerRecord.entity.SgAnswerRecord;
import vip.xiaonuo.biz.modular.answerRecord.service.SgAnswerRecordService;
import vip.xiaonuo.biz.modular.question.entity.SgQuestion;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.biz.modular.answerStatistics.entity.SgAnswerStatistics;
import vip.xiaonuo.biz.modular.answerStatistics.param.SgAnswerStatisticsAddParam;
import vip.xiaonuo.biz.modular.answerStatistics.param.SgAnswerStatisticsEditParam;
import vip.xiaonuo.biz.modular.answerStatistics.param.SgAnswerStatisticsIdParam;
import vip.xiaonuo.biz.modular.answerStatistics.param.SgAnswerStatisticsPageParam;
import vip.xiaonuo.biz.modular.answerStatistics.service.SgAnswerStatisticsService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 答题统计控制器
 *
 * @author byc
 * @date  2025/02/08 15:08
 */
@Tag(name = "答题统计控制器")
@RestController
@Validated
public class SgAnswerStatisticsController {

    @Resource
    private SgAnswerStatisticsService sgAnswerStatisticsService;

    @Resource
    private SgAnswerRecordService sgAnswerRecordService;

    /**
     * 获取答题统计分页
     *
     * @author byc
     * @date  2025/02/08 15:08
     */
    @Operation(summary = "获取答题统计分页")
    @SaCheckPermission("/biz/answerStatistics/page")
    @GetMapping("/biz/answerStatistics/page")
    public CommonResult<Page<SgAnswerStatistics>> page(SgAnswerStatisticsPageParam sgAnswerStatisticsPageParam) {
        return CommonResult.data(sgAnswerStatisticsService.page(sgAnswerStatisticsPageParam));
    }

    /**
     * 添加答题统计
     *
     * @author byc
     * @date  2025/02/08 15:08
     */
    @Operation(summary = "添加答题统计")
    @CommonLog("添加答题统计")
    @SaCheckPermission("/biz/answerStatistics/add")
    @PostMapping("/biz/answerStatistics/add")
    public CommonResult<String> add(@RequestBody @Valid SgAnswerStatisticsAddParam sgAnswerStatisticsAddParam) {
        sgAnswerStatisticsService.add(sgAnswerStatisticsAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑答题统计
     *
     * @author byc
     * @date  2025/02/08 15:08
     */
    @Operation(summary = "编辑答题统计")
    @CommonLog("编辑答题统计")
    @SaCheckPermission("/biz/answerStatistics/edit")
    @PostMapping("/biz/answerStatistics/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgAnswerStatisticsEditParam sgAnswerStatisticsEditParam) {
        sgAnswerStatisticsService.edit(sgAnswerStatisticsEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除答题统计
     *
     * @author byc
     * @date  2025/02/08 15:08
     */
    @Operation(summary = "删除答题统计")
    @CommonLog("删除答题统计")
    @SaCheckPermission("/biz/answerStatistics/delete")
    @PostMapping("/biz/answerStatistics/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgAnswerStatisticsIdParam> sgAnswerStatisticsIdParamList) {
        sgAnswerStatisticsService.delete(sgAnswerStatisticsIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取答题统计详情
     *
     * @author byc
     * @date  2025/02/08 15:08
     */
    @Operation(summary = "获取答题统计详情")
    @SaCheckPermission("/biz/answerStatistics/detail")
    @GetMapping("/biz/answerStatistics/detail")
    public CommonResult<SgAnswerStatistics> detail(@Valid SgAnswerStatisticsIdParam sgAnswerStatisticsIdParam) {
        return CommonResult.data(sgAnswerStatisticsService.detail(sgAnswerStatisticsIdParam));
    }

    /**
     * 获取答题统计相关的答题记录
     */
    @Operation(summary = "答题统计-查看试卷")
    @SaCheckPermission("/biz/answerStatistics/answerRecords")
    @GetMapping("/biz/answerStatistics/answerRecords")
    public CommonResult<List<SgAnswerRecord>> answerRecords(@Valid SgAnswerStatisticsIdParam sgAnswerStatisticsIdParam) {
        return CommonResult.data(sgAnswerRecordService.answerRecords(sgAnswerStatisticsIdParam));
    }
}
