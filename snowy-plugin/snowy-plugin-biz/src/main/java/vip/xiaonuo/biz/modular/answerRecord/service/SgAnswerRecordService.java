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
package vip.xiaonuo.biz.modular.answerRecord.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.answerRecord.entity.SgAnswerRecord;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordAddParam;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordEditParam;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordIdParam;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordPageParam;
import vip.xiaonuo.biz.modular.answerStatistics.entity.SgAnswerStatistics;
import vip.xiaonuo.biz.modular.answerStatistics.param.SgAnswerStatisticsIdParam;

import java.util.List;

/**
 * 答题记录Service接口
 *
 * @author byc
 * @date  2025/02/08 15:06
 **/
public interface SgAnswerRecordService extends IService<SgAnswerRecord> {

    /**
     * 获取答题记录分页
     *
     * @author byc
     * @date  2025/02/08 15:06
     */
    Page<SgAnswerRecord> page(SgAnswerRecordPageParam sgAnswerRecordPageParam);

    /**
     * 添加答题记录
     *
     * @author byc
     * @date  2025/02/08 15:06
     */
    void add(SgAnswerRecordAddParam sgAnswerRecordAddParam);

    /**
     * 编辑答题记录
     *
     * @author byc
     * @date  2025/02/08 15:06
     */
    void edit(SgAnswerRecordEditParam sgAnswerRecordEditParam);

    /**
     * 删除答题记录
     *
     * @author byc
     * @date  2025/02/08 15:06
     */
    void delete(List<SgAnswerRecordIdParam> sgAnswerRecordIdParamList);

    /**
     * 获取答题记录详情
     *
     * @author byc
     * @date  2025/02/08 15:06
     */
    SgAnswerRecord detail(SgAnswerRecordIdParam sgAnswerRecordIdParam);

    /**
     * 获取答题记录详情
     *
     * @author byc
     * @date  2025/02/08 15:06
     **/
    SgAnswerRecord queryEntity(String id);

    /**
     * 提交试卷
     * @param answerRecordAddParamList 试题参数
     * @return 用户试卷得分
     */
    SgAnswerStatistics submitAnswer(List<SgAnswerRecordAddParam> answerRecordAddParamList);

    /**
     * 获取答题记录
     * @param sgAnswerStatisticsIdParam 答题统计ID
     * @return List<SgAnswerRecord>
     */
    List<SgAnswerRecord> answerRecords(SgAnswerStatisticsIdParam sgAnswerStatisticsIdParam);
}
