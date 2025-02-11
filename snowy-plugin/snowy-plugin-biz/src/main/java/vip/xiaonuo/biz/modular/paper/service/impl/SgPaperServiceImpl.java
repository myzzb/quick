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
package vip.xiaonuo.biz.modular.paper.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.biz.modular.question.entity.SgQuestion;
import vip.xiaonuo.biz.modular.question.mapper.SgQuestionMapper;
import vip.xiaonuo.biz.modular.user.entity.BizUser;
import vip.xiaonuo.biz.modular.user.param.BizUserIdParam;
import vip.xiaonuo.biz.modular.user.service.BizUserService;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.paper.entity.SgPaper;
import vip.xiaonuo.biz.modular.paper.mapper.SgPaperMapper;
import vip.xiaonuo.biz.modular.paper.param.SgPaperAddParam;
import vip.xiaonuo.biz.modular.paper.param.SgPaperEditParam;
import vip.xiaonuo.biz.modular.paper.param.SgPaperIdParam;
import vip.xiaonuo.biz.modular.paper.param.SgPaperPageParam;
import vip.xiaonuo.biz.modular.paper.service.SgPaperService;

import java.util.List;

/**
 * 试卷表Service接口实现类
 *
 * @author byc
 * @date  2025/02/07 15:58
 **/
@Service
public class SgPaperServiceImpl extends ServiceImpl<SgPaperMapper, SgPaper> implements SgPaperService {

    @Resource
    private SgQuestionMapper sgQuestionMapper;

    @Resource
    private BizUserService bizUserService;

    @Override
    public Page<SgPaper> page(SgPaperPageParam sgPaperPageParam) {
        QueryWrapper<SgPaper> queryWrapper = new QueryWrapper<SgPaper>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgPaperPageParam.getYwfl())) {
            queryWrapper.lambda().like(SgPaper::getYwfl, sgPaperPageParam.getYwfl());
        }
        if(ObjectUtil.isNotEmpty(sgPaperPageParam.getName())) {
            queryWrapper.lambda().like(SgPaper::getName, sgPaperPageParam.getName());
        }
        if(ObjectUtil.isNotEmpty(sgPaperPageParam.getStatus())) {
            queryWrapper.lambda().eq(SgPaper::getStatus, sgPaperPageParam.getStatus());
        }
        if(ObjectUtil.isAllNotEmpty(sgPaperPageParam.getSortField(), sgPaperPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgPaperPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgPaperPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgPaperPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgPaper::getSortCode);
        }
        Page<SgPaper> page = this.page(CommonPageRequest.defaultPage(), queryWrapper);
        page.getRecords().forEach(item -> {
            item.setQuestionCount(0);
            sgQuestionMapper.selectList(new QueryWrapper<SgQuestion>().lambda().eq(SgQuestion::getPaperId, item.getId())).forEach(question -> {
                item.setTotalScore(item.getTotalScore() + question.getScore());
                item.setQuestionCount(item.getQuestionCount() + 1);
            });
            BizUserIdParam bizUserIdParam = new BizUserIdParam();
            bizUserIdParam.setId(item.getCreateUser());
            BizUser user = bizUserService.detail(bizUserIdParam);
            item.setCreateUser(user.getName());
        });
        return page;
    }

    @Override
    public List<SgPaper> allList() {
        return this.list(new LambdaQueryWrapper<SgPaper>().eq(SgPaper::getDeleteFlag, "NOT_DELETE").orderByDesc(SgPaper::getCreateTime));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgPaperAddParam sgPaperAddParam) {
        SgPaper sgPaper = BeanUtil.toBean(sgPaperAddParam, SgPaper.class);
        this.save(sgPaper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgPaperEditParam sgPaperEditParam) {
        SgPaper sgPaper = this.queryEntity(sgPaperEditParam.getId());
        BeanUtil.copyProperties(sgPaperEditParam, sgPaper);
        this.updateById(sgPaper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgPaperIdParam> sgPaperIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgPaperIdParamList, SgPaperIdParam::getId));
    }

    @Override
    public SgPaper detail(SgPaperIdParam sgPaperIdParam) {
        return this.queryEntity(sgPaperIdParam.getId());
    }

    @Override
    public SgPaper queryEntity(String id) {
        SgPaper sgPaper = this.getById(id);
        if(ObjectUtil.isEmpty(sgPaper)) {
            throw new CommonException("试卷表不存在，id值为：{}", id);
        }
        return sgPaper;
    }
}
