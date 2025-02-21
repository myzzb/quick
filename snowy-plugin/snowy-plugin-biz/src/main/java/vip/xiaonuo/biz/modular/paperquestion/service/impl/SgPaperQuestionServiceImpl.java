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
package vip.xiaonuo.biz.modular.paperquestion.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.paperquestion.entity.SgPaperQuestion;
import vip.xiaonuo.biz.modular.paperquestion.mapper.SgPaperQuestionMapper;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionAddParam;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionEditParam;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionIdParam;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionPageParam;
import vip.xiaonuo.biz.modular.paperquestion.service.SgPaperQuestionService;

import java.util.List;

/**
 * 试卷试题关系表Service接口实现类
 *
 * @author byc
 * @date  2025/02/21 11:35
 **/
@Service
public class SgPaperQuestionServiceImpl extends ServiceImpl<SgPaperQuestionMapper, SgPaperQuestion> implements SgPaperQuestionService {

    @Override
    public Page<SgPaperQuestion> page(SgPaperQuestionPageParam sgPaperQuestionPageParam) {
        QueryWrapper<SgPaperQuestion> queryWrapper = new QueryWrapper<SgPaperQuestion>().checkSqlInjection();
        if(ObjectUtil.isAllNotEmpty(sgPaperQuestionPageParam.getSortField(), sgPaperQuestionPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgPaperQuestionPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgPaperQuestionPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgPaperQuestionPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgPaperQuestion::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgPaperQuestionAddParam sgPaperQuestionAddParam) {
        SgPaperQuestion sgPaperQuestion = BeanUtil.toBean(sgPaperQuestionAddParam, SgPaperQuestion.class);
        this.save(sgPaperQuestion);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgPaperQuestionEditParam sgPaperQuestionEditParam) {
        SgPaperQuestion sgPaperQuestion = this.queryEntity(sgPaperQuestionEditParam.getId());
        BeanUtil.copyProperties(sgPaperQuestionEditParam, sgPaperQuestion);
        this.updateById(sgPaperQuestion);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgPaperQuestionIdParam> sgPaperQuestionIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgPaperQuestionIdParamList, SgPaperQuestionIdParam::getId));
    }

    @Override
    public SgPaperQuestion detail(SgPaperQuestionIdParam sgPaperQuestionIdParam) {
        return this.queryEntity(sgPaperQuestionIdParam.getId());
    }

    @Override
    public SgPaperQuestion queryEntity(String id) {
        SgPaperQuestion sgPaperQuestion = this.getById(id);
        if(ObjectUtil.isEmpty(sgPaperQuestion)) {
            throw new CommonException("试卷试题关系表不存在，id值为：{}", id);
        }
        return sgPaperQuestion;
    }
}
