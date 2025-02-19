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
package vip.xiaonuo.biz.modular.bq.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.biz.modular.bq.param.SgBqEditParam;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.bq.entity.SgBq;
import vip.xiaonuo.biz.modular.bq.mapper.SgBqMapper;
import vip.xiaonuo.biz.modular.bq.param.SgBqAddParam;
import vip.xiaonuo.biz.modular.bq.param.SgBqIdParam;
import vip.xiaonuo.biz.modular.bq.param.SgBqPageParam;
import vip.xiaonuo.biz.modular.bq.service.SgBqService;

import java.util.List;

/**
 * 标签管理Service接口实现类
 *
 * @author zzb
 * @date  2025/02/12 11:55
 **/
@Service
public class SgBqServiceImpl extends ServiceImpl<SgBqMapper, SgBq> implements SgBqService {

    @Override
    public Page<SgBq> page(SgBqPageParam sgBqPageParam) {
        QueryWrapper<SgBq> queryWrapper = new QueryWrapper<SgBq>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgBqPageParam.getName())) {
            queryWrapper.lambda().like(SgBq::getName, sgBqPageParam.getName());
        }
        if(ObjectUtil.isNotEmpty(sgBqPageParam.getColor())) {
            queryWrapper.lambda().like(SgBq::getColor, sgBqPageParam.getColor());
        }
        if(ObjectUtil.isAllNotEmpty(sgBqPageParam.getSortField(), sgBqPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgBqPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgBqPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgBqPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgBq::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgBqAddParam sgBqAddParam) {
        SgBq sgBq = BeanUtil.toBean(sgBqAddParam, SgBq.class);
        this.save(sgBq);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgBqEditParam sgBqEditParam) {
        SgBq sgBq = this.queryEntity(sgBqEditParam.getBqId());
        BeanUtil.copyProperties(sgBqEditParam, sgBq);
        this.updateById(sgBq);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgBqIdParam> sgBqIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgBqIdParamList, SgBqIdParam::getBqId));
    }

    @Override
    public SgBq detail(SgBqIdParam sgBqIdParam) {
        return this.queryEntity(sgBqIdParam.getBqId());
    }

    @Override
    public SgBq queryEntity(String id) {
        SgBq sgBq = this.getById(id);
        if(ObjectUtil.isEmpty(sgBq)) {
            throw new CommonException("标签管理不存在，id值为：{}", id);
        }
        return sgBq;
    }
}
