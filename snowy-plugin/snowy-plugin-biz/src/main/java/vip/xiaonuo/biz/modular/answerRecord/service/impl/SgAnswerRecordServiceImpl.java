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
package vip.xiaonuo.biz.modular.answerRecord.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.biz.modular.answerStatistics.entity.SgAnswerStatistics;
import vip.xiaonuo.biz.modular.answerStatistics.mapper.SgAnswerStatisticsMapper;
import vip.xiaonuo.biz.modular.answerStatistics.param.SgAnswerStatisticsIdParam;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.answerRecord.entity.SgAnswerRecord;
import vip.xiaonuo.biz.modular.answerRecord.mapper.SgAnswerRecordMapper;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordAddParam;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordEditParam;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordIdParam;
import vip.xiaonuo.biz.modular.answerRecord.param.SgAnswerRecordPageParam;
import vip.xiaonuo.biz.modular.answerRecord.service.SgAnswerRecordService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 答题记录Service接口实现类
 *
 * @author byc
 * @date  2025/02/08 15:06
 **/
@Service
public class SgAnswerRecordServiceImpl extends ServiceImpl<SgAnswerRecordMapper, SgAnswerRecord> implements SgAnswerRecordService {

    /**
     * 答题统计Mapper接口
     */
    @Resource
    private SgAnswerStatisticsMapper sgAnswerStatisticsMapper;

    @Override
    public Page<SgAnswerRecord> page(SgAnswerRecordPageParam sgAnswerRecordPageParam) {
        QueryWrapper<SgAnswerRecord> queryWrapper = new QueryWrapper<SgAnswerRecord>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgAnswerRecordPageParam.getUserName())) {
            queryWrapper.lambda().like(SgAnswerRecord::getUserName, sgAnswerRecordPageParam.getUserName());
        }
        if(ObjectUtil.isAllNotEmpty(sgAnswerRecordPageParam.getSortField(), sgAnswerRecordPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgAnswerRecordPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgAnswerRecordPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgAnswerRecordPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgAnswerRecord::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgAnswerRecordAddParam sgAnswerRecordAddParam) {
        SgAnswerRecord sgAnswerRecord = BeanUtil.toBean(sgAnswerRecordAddParam, SgAnswerRecord.class);
        this.save(sgAnswerRecord);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgAnswerRecordEditParam sgAnswerRecordEditParam) {
        SgAnswerRecord sgAnswerRecord = this.queryEntity(sgAnswerRecordEditParam.getId());
        BeanUtil.copyProperties(sgAnswerRecordEditParam, sgAnswerRecord);
        this.updateById(sgAnswerRecord);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgAnswerRecordIdParam> sgAnswerRecordIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgAnswerRecordIdParamList, SgAnswerRecordIdParam::getId));
    }

    @Override
    public SgAnswerRecord detail(SgAnswerRecordIdParam sgAnswerRecordIdParam) {
        return this.queryEntity(sgAnswerRecordIdParam.getId());
    }

    @Override
    public SgAnswerRecord queryEntity(String id) {
        SgAnswerRecord sgAnswerRecord = this.getById(id);
        if(ObjectUtil.isEmpty(sgAnswerRecord)) {
            throw new CommonException("答题记录不存在，id值为：{}", id);
        }
        return sgAnswerRecord;
    }

    @Override
    public SgAnswerStatistics submitAnswer(List<SgAnswerRecordAddParam>answerRecordAddParamList) {
        // 获取年月日
        String yyyyMMdd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 新增考试统计表
        SgAnswerRecordAddParam sgAnswerRecordAddParam = answerRecordAddParamList.get(0);
        SgAnswerStatistics sgAnswerStatistics = BeanUtil.toBean(sgAnswerRecordAddParam, SgAnswerStatistics.class);
        sgAnswerStatistics.setName(sgAnswerRecordAddParam.getYwfl() + yyyyMMdd);
        System.out.println("JSON.toJSONString(sgAnswerStatistics) = " + JSON.toJSONString(sgAnswerStatistics));
        int insertResult = sgAnswerStatisticsMapper.insert(sgAnswerStatistics);
        if (insertResult > 0) {
            // 使用获取到的新ID查询用户
            SgAnswerStatistics answerStatistics = sgAnswerStatisticsMapper.selectById(sgAnswerStatistics.getId());
            System.out.println("修改前--答题统计entity：" + answerStatistics);
            System.out.println("答题统计ID" + answerStatistics.getId());
            // 新增答题记录表
            int userTotalScore = 0;
            for (SgAnswerRecordAddParam answerRecord : answerRecordAddParamList) {
                SgAnswerRecord sgAnswerRecord = BeanUtil.toBean(answerRecord, SgAnswerRecord.class);
                sgAnswerRecord.setStatisticsId(answerStatistics.getId());
                // 获取正确答案
                String answer = sgAnswerRecord.getAnswer();
                // 获取用户答案
                String userAnswer = sgAnswerRecord.getUserAnswer();
                // 获取分值
                Integer score = sgAnswerRecord.getScore();
                // 判断用户答案是否正确
                if (answer.equals(userAnswer)) {
                    sgAnswerRecord.setUserScore(score);
                    userTotalScore += score;
                }
                this.save(sgAnswerRecord);
            }

            // 将用户总分存入；答题统计表
            answerStatistics.setUserScore(userTotalScore);
            sgAnswerStatisticsMapper.updateById(answerStatistics);
            System.out.println("用户总分：" + userTotalScore);
            System.out.println("修改后--答题统计entity：" + answerStatistics);
            return answerStatistics;
        } else {
            throw new CommonException("试卷提交失败");
        }
    }


    @Override
    public List<SgAnswerRecord> answerRecords(SgAnswerStatisticsIdParam sgAnswerStatisticsIdParam) {
        if(ObjectUtil.isEmpty(sgAnswerStatisticsIdParam.getId())) {
            throw new CommonException("请检查参数是否正确");
        }
        LambdaQueryWrapper<SgAnswerRecord> lambdaQueryWrapper = new QueryWrapper<SgAnswerRecord>().checkSqlInjection().lambda().eq(SgAnswerRecord::getStatisticsId, sgAnswerStatisticsIdParam.getId()).orderByAsc(SgAnswerRecord::getSortCode);
        return this.list(lambdaQueryWrapper);
    }
}
