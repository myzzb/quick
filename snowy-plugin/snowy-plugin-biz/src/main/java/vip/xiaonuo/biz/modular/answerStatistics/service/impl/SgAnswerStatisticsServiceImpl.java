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
package vip.xiaonuo.biz.modular.answerStatistics.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.biz.modular.answerRecord.entity.SgAnswerRecord;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.answerStatistics.entity.SgAnswerStatistics;
import vip.xiaonuo.biz.modular.answerStatistics.mapper.SgAnswerStatisticsMapper;
import vip.xiaonuo.biz.modular.answerStatistics.param.SgAnswerStatisticsAddParam;
import vip.xiaonuo.biz.modular.answerStatistics.param.SgAnswerStatisticsEditParam;
import vip.xiaonuo.biz.modular.answerStatistics.param.SgAnswerStatisticsIdParam;
import vip.xiaonuo.biz.modular.answerStatistics.param.SgAnswerStatisticsPageParam;
import vip.xiaonuo.biz.modular.answerStatistics.service.SgAnswerStatisticsService;

import java.util.List;

/**
 * 答题统计Service接口实现类
 *
 * @author byc
 * @date  2025/02/08 15:08
 **/
@Service
public class SgAnswerStatisticsServiceImpl extends ServiceImpl<SgAnswerStatisticsMapper, SgAnswerStatistics> implements SgAnswerStatisticsService {

    @Override
    public Page<SgAnswerStatistics> page(SgAnswerStatisticsPageParam sgAnswerStatisticsPageParam) {
        QueryWrapper<SgAnswerStatistics> queryWrapper = new QueryWrapper<SgAnswerStatistics>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgAnswerStatisticsPageParam.getPaperName())) {
            queryWrapper.lambda().like(SgAnswerStatistics::getPaperName, sgAnswerStatisticsPageParam.getPaperName());
        }
        if(ObjectUtil.isNotEmpty(sgAnswerStatisticsPageParam.getUserName())) {
            queryWrapper.lambda().like(SgAnswerStatistics::getUserName, sgAnswerStatisticsPageParam.getUserName());
        }
        if(ObjectUtil.isAllNotEmpty(sgAnswerStatisticsPageParam.getSortField(), sgAnswerStatisticsPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgAnswerStatisticsPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgAnswerStatisticsPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgAnswerStatisticsPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgAnswerStatistics::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgAnswerStatisticsAddParam sgAnswerStatisticsAddParam) {
        SgAnswerStatistics sgAnswerStatistics = BeanUtil.toBean(sgAnswerStatisticsAddParam, SgAnswerStatistics.class);
        this.save(sgAnswerStatistics);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgAnswerStatisticsEditParam sgAnswerStatisticsEditParam) {
        SgAnswerStatistics sgAnswerStatistics = this.queryEntity(sgAnswerStatisticsEditParam.getId());
        BeanUtil.copyProperties(sgAnswerStatisticsEditParam, sgAnswerStatistics);
        this.updateById(sgAnswerStatistics);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgAnswerStatisticsIdParam> sgAnswerStatisticsIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgAnswerStatisticsIdParamList, SgAnswerStatisticsIdParam::getId));
    }

    @Override
    public SgAnswerStatistics detail(SgAnswerStatisticsIdParam sgAnswerStatisticsIdParam) {
        return this.queryEntity(sgAnswerStatisticsIdParam.getId());
    }

    @Override
    public SgAnswerStatistics queryEntity(String id) {
        SgAnswerStatistics sgAnswerStatistics = this.getById(id);
        if(ObjectUtil.isEmpty(sgAnswerStatistics)) {
            throw new CommonException("答题统计不存在，id值为：{}", id);
        }
        return sgAnswerStatistics;
    }
}
