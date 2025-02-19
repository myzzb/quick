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
package vip.xiaonuo.biz.modular.bqre.service.impl;

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
import vip.xiaonuo.biz.modular.bqre.entity.SgBqRe;
import vip.xiaonuo.biz.modular.bqre.mapper.SgBqReMapper;
import vip.xiaonuo.biz.modular.bqre.param.SgBqReAddParam;
import vip.xiaonuo.biz.modular.bqre.param.SgBqReEditParam;
import vip.xiaonuo.biz.modular.bqre.param.SgBqReIdParam;
import vip.xiaonuo.biz.modular.bqre.param.SgBqRePageParam;
import vip.xiaonuo.biz.modular.bqre.service.SgBqReService;

import java.util.List;

/**
 * 事故标签关联Service接口实现类
 *
 * @author zzb
 * @date  2025/02/12 15:01
 **/
@Service
public class SgBqReServiceImpl extends ServiceImpl<SgBqReMapper, SgBqRe> implements SgBqReService {

    @Override
    public Page<SgBqRe> page(SgBqRePageParam sgBqRePageParam) {
        QueryWrapper<SgBqRe> queryWrapper = new QueryWrapper<SgBqRe>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgBqRePageParam.getSgId())) {
            queryWrapper.lambda().like(SgBqRe::getSgId, sgBqRePageParam.getSgId());
        }
        if(ObjectUtil.isNotEmpty(sgBqRePageParam.getBqId())) {
            queryWrapper.lambda().like(SgBqRe::getBqId, sgBqRePageParam.getBqId());
        }
        if(ObjectUtil.isAllNotEmpty(sgBqRePageParam.getSortField(), sgBqRePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgBqRePageParam.getSortOrder());
            queryWrapper.orderBy(true, sgBqRePageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgBqRePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgBqRe::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgBqReAddParam sgBqReAddParam) {
        SgBqRe sgBqRe = BeanUtil.toBean(sgBqReAddParam, SgBqRe.class);
        this.save(sgBqRe);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgBqReEditParam sgBqReEditParam) {
        SgBqRe sgBqRe = this.queryEntity(sgBqReEditParam.getId());
        BeanUtil.copyProperties(sgBqReEditParam, sgBqRe);
        this.updateById(sgBqRe);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgBqReIdParam> sgBqReIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgBqReIdParamList, SgBqReIdParam::getId));
    }

    @Override
    public SgBqRe detail(SgBqReIdParam sgBqReIdParam) {
        return this.queryEntity(sgBqReIdParam.getId());
    }

    @Override
    public SgBqRe queryEntity(String id) {
        SgBqRe sgBqRe = this.getById(id);
        if(ObjectUtil.isEmpty(sgBqRe)) {
            throw new CommonException("事故标签关联不存在，id值为：{}", id);
        }
        return sgBqRe;
    }
}
