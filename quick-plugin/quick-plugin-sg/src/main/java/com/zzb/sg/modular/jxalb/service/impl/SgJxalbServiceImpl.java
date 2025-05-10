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
package com.zzb.sg.modular.jxalb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzb.sg.modular.jxalb.entity.SgJxalb;
import com.zzb.sg.modular.jxalb.mapper.SgJxalbMapper;
import com.zzb.sg.modular.jxalb.param.SgJxalbAddParam;
import com.zzb.sg.modular.jxalb.param.SgJxalbEditParam;
import com.zzb.sg.modular.jxalb.param.SgJxalbIdParam;
import com.zzb.sg.modular.jxalb.param.SgJxalbPageParam;
import com.zzb.sg.modular.jxalb.service.SgJxalbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zzb.common.enums.CommonSortOrderEnum;
import com.zzb.common.exception.CommonException;
import com.zzb.common.page.CommonPageRequest;

import java.util.List;

/**
 * 案例表 Service接口实现类
 *
 * @author zzb
 * @date  2025/01/08 16:17
 **/
@Service
public class SgJxalbServiceImpl extends ServiceImpl<SgJxalbMapper, SgJxalb> implements SgJxalbService {

    @Override
    public Page<SgJxalb> page(SgJxalbPageParam sgJxalbPageParam) {
        QueryWrapper<SgJxalb> queryWrapper = new QueryWrapper<SgJxalb>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgJxalbPageParam.getSgLx())) {
            queryWrapper.lambda().like(SgJxalb::getSgLx, sgJxalbPageParam.getSgLx());
        }
        if(ObjectUtil.isNotEmpty(sgJxalbPageParam.getSgFl())) {
            queryWrapper.lambda().eq(SgJxalb::getSgFl, sgJxalbPageParam.getSgFl());
        }
        if(ObjectUtil.isNotEmpty(sgJxalbPageParam.getSgDw())) {
            queryWrapper.lambda().like(SgJxalb::getSgDw, sgJxalbPageParam.getSgDw());
        }
        if(ObjectUtil.isNotEmpty(sgJxalbPageParam.getSgDj())) {
            queryWrapper.lambda().eq(SgJxalb::getSgDj, sgJxalbPageParam.getSgDj());
        }
        if(ObjectUtil.isNotEmpty(sgJxalbPageParam.getStartJxalFssj()) && ObjectUtil.isNotEmpty(sgJxalbPageParam.getEndJxalFssj())) {
            queryWrapper.lambda().between(SgJxalb::getJxalFssj, sgJxalbPageParam.getStartJxalFssj(), sgJxalbPageParam.getEndJxalFssj());
        }
        if(ObjectUtil.isAllNotEmpty(sgJxalbPageParam.getSortField(), sgJxalbPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgJxalbPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgJxalbPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgJxalbPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgJxalb::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgJxalbAddParam sgJxalbAddParam) {
        SgJxalb sgJxalb = BeanUtil.toBean(sgJxalbAddParam, SgJxalb.class);
        this.save(sgJxalb);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgJxalbEditParam sgJxalbEditParam) {
        SgJxalb sgJxalb = this.queryEntity(sgJxalbEditParam.getJxalId());
        BeanUtil.copyProperties(sgJxalbEditParam, sgJxalb);
        this.updateById(sgJxalb);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgJxalbIdParam> sgJxalbIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgJxalbIdParamList, SgJxalbIdParam::getJxalId));
    }

    @Override
    public SgJxalb detail(SgJxalbIdParam sgJxalbIdParam) {
        return this.queryEntity(sgJxalbIdParam.getJxalId());
    }

    @Override
    public SgJxalb queryEntity(String id) {
        SgJxalb sgJxalb = this.getById(id);
        if(ObjectUtil.isEmpty(sgJxalb)) {
            throw new CommonException("案例表 不存在，id值为：{}", id);
        }
        return sgJxalb;
    }
}
