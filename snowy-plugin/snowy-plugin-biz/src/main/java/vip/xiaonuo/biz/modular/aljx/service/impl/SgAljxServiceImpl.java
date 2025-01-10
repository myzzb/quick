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
package vip.xiaonuo.biz.modular.aljx.service.impl;

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
import vip.xiaonuo.biz.modular.aljx.entity.SgAljx;
import vip.xiaonuo.biz.modular.aljx.mapper.SgAljxMapper;
import vip.xiaonuo.biz.modular.aljx.param.SgAljxAddParam;
import vip.xiaonuo.biz.modular.aljx.param.SgAljxEditParam;
import vip.xiaonuo.biz.modular.aljx.param.SgAljxIdParam;
import vip.xiaonuo.biz.modular.aljx.param.SgAljxPageParam;
import vip.xiaonuo.biz.modular.aljx.service.SgAljxService;

import java.util.List;

/**
 * 案例教学Service接口实现类
 *
 * @author byc
 * @date  2025/01/09 17:49
 **/
@Service
public class SgAljxServiceImpl extends ServiceImpl<SgAljxMapper, SgAljx> implements SgAljxService {

    @Override
    public Page<SgAljx> page(SgAljxPageParam sgAljxPageParam) {
        QueryWrapper<SgAljx> queryWrapper = new QueryWrapper<SgAljx>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgAljxPageParam.getSglx())) {
            queryWrapper.lambda().like(SgAljx::getSglx, sgAljxPageParam.getSglx());
        }
        if(ObjectUtil.isNotEmpty(sgAljxPageParam.getSgfl())) {
            queryWrapper.lambda().like(SgAljx::getSgfl, sgAljxPageParam.getSgfl());
        }
        if(ObjectUtil.isNotEmpty(sgAljxPageParam.getSgdj())) {
            queryWrapper.lambda().like(SgAljx::getSgdj, sgAljxPageParam.getSgdj());
        }
        if(ObjectUtil.isNotEmpty(sgAljxPageParam.getStartSgfssj()) && ObjectUtil.isNotEmpty(sgAljxPageParam.getEndSgfssj())) {
            queryWrapper.lambda().between(SgAljx::getSgfssj, sgAljxPageParam.getStartSgfssj(), sgAljxPageParam.getEndSgfssj());
        }
        if(ObjectUtil.isNotEmpty(sgAljxPageParam.getStartCreateTime()) && ObjectUtil.isNotEmpty(sgAljxPageParam.getEndCreateTime())) {
            queryWrapper.lambda().between(SgAljx::getCreateTime, sgAljxPageParam.getStartCreateTime(), sgAljxPageParam.getEndCreateTime());
        }
        if(ObjectUtil.isAllNotEmpty(sgAljxPageParam.getSortField(), sgAljxPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgAljxPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgAljxPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgAljxPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgAljx::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgAljxAddParam sgAljxAddParam) {
        SgAljx sgAljx = BeanUtil.toBean(sgAljxAddParam, SgAljx.class);
        this.save(sgAljx);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgAljxEditParam sgAljxEditParam) {
        SgAljx sgAljx = this.queryEntity(sgAljxEditParam.getAljxId());
        BeanUtil.copyProperties(sgAljxEditParam, sgAljx);
        this.updateById(sgAljx);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgAljxIdParam> sgAljxIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgAljxIdParamList, SgAljxIdParam::getAljxId));
    }

    @Override
    public SgAljx detail(SgAljxIdParam sgAljxIdParam) {
        return this.queryEntity(sgAljxIdParam.getAljxId());
    }

    @Override
    public SgAljx queryEntity(String id) {
        SgAljx sgAljx = this.getById(id);
        if(ObjectUtil.isEmpty(sgAljx)) {
            throw new CommonException("案例教学不存在，id值为：{}", id);
        }
        return sgAljx;
    }
}
