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
package vip.xiaonuo.biz.modular.question.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.biz.modular.paper.param.SgPaperIdParam;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.question.entity.SgQuestion;
import vip.xiaonuo.biz.modular.question.mapper.SgQuestionMapper;
import vip.xiaonuo.biz.modular.question.param.SgQuestionAddParam;
import vip.xiaonuo.biz.modular.question.param.SgQuestionEditParam;
import vip.xiaonuo.biz.modular.question.param.SgQuestionIdParam;
import vip.xiaonuo.biz.modular.question.param.SgQuestionPageParam;
import vip.xiaonuo.biz.modular.question.service.SgQuestionService;

import java.util.List;

/**
 * 试题Service接口实现类
 *
 * @author byc
 * @date  2025/02/07 17:43
 **/
@Service
public class SgQuestionServiceImpl extends ServiceImpl<SgQuestionMapper, SgQuestion> implements SgQuestionService {

    @Override
    public Page<SgQuestion> page(SgQuestionPageParam sgQuestionPageParam) {
        QueryWrapper<SgQuestion> queryWrapper = new QueryWrapper<SgQuestion>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgQuestionPageParam.getYwfl())) {
            queryWrapper.lambda().like(SgQuestion::getYwfl, sgQuestionPageParam.getYwfl());
        }
        if(ObjectUtil.isNotEmpty(sgQuestionPageParam.getQuestionName())) {
            queryWrapper.lambda().like(SgQuestion::getQuestionName, sgQuestionPageParam.getQuestionName());
        }
        if(ObjectUtil.isNotEmpty(sgQuestionPageParam.getType())) {
            queryWrapper.lambda().eq(SgQuestion::getType, sgQuestionPageParam.getType());
        }
        if(ObjectUtil.isNotEmpty(sgQuestionPageParam.getStatus())) {
            queryWrapper.lambda().eq(SgQuestion::getStatus, sgQuestionPageParam.getStatus());
        }
        if(ObjectUtil.isAllNotEmpty(sgQuestionPageParam.getSortField(), sgQuestionPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgQuestionPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgQuestionPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgQuestionPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgQuestion::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgQuestionAddParam sgQuestionAddParam) {
        SgQuestion sgQuestion = BeanUtil.toBean(sgQuestionAddParam, SgQuestion.class);
        this.save(sgQuestion);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgQuestionEditParam sgQuestionEditParam) {
        SgQuestion sgQuestion = this.queryEntity(sgQuestionEditParam.getId());
        BeanUtil.copyProperties(sgQuestionEditParam, sgQuestion);
        this.updateById(sgQuestion);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgQuestionIdParam> sgQuestionIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgQuestionIdParamList, SgQuestionIdParam::getId));
    }

    @Override
    public SgQuestion detail(SgQuestionIdParam sgQuestionIdParam) {
        return this.queryEntity(sgQuestionIdParam.getId());
    }

    @Override
    public SgQuestion queryEntity(String id) {
        SgQuestion sgQuestion = this.getById(id);
        if(ObjectUtil.isEmpty(sgQuestion)) {
            throw new CommonException("试题不存在，id值为：{}", id);
        }
        return sgQuestion;
    }

    @Override
    public List<SgQuestion> question(SgPaperIdParam sgPaperIdParam) {
//        if(ObjectUtil.isEmpty(sgPaperIdParam.getId())) {
//            throw new CommonException("请检查参数是否正确");
//        }
//        LambdaQueryWrapper<SgQuestion> lambdaQueryWrapper = new QueryWrapper<SgQuestion>().checkSqlInjection().lambda().eq(SgQuestion::getPaperId, sgPaperIdParam.getId()).orderByAsc(SgQuestion::getSortCode);
//        return this.list(lambdaQueryWrapper);
        return null;
    }


    @Override
    public List<SgQuestion> list(String ywfl) {
        if(ObjectUtil.isEmpty(ywfl)) {
            throw new CommonException("请检查参数是否正确");
        }
        List<SgQuestion> randomList = this.baseMapper.selectRandomList(ywfl);
        return randomList;
    }
}
