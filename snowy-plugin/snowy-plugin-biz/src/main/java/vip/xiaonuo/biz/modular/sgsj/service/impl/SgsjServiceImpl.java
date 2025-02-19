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
package vip.xiaonuo.biz.modular.sgsj.service.impl;

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
import vip.xiaonuo.biz.modular.sgsj.entity.Sgsj;
import vip.xiaonuo.biz.modular.sgsj.mapper.SgsjMapper;
import vip.xiaonuo.biz.modular.sgsj.param.SgsjAddParam;
import vip.xiaonuo.biz.modular.sgsj.param.SgsjEditParam;
import vip.xiaonuo.biz.modular.sgsj.param.SgsjIdParam;
import vip.xiaonuo.biz.modular.sgsj.param.SgsjPageParam;
import vip.xiaonuo.biz.modular.sgsj.service.SgsjService;

import java.util.List;

/**
 * 事故数据Service接口实现类
 *
 * @author zzb
 * @date  2025/02/18 15:46
 **/
@Service
public class SgsjServiceImpl extends ServiceImpl<SgsjMapper, Sgsj> implements SgsjService {

    @Override
    public Page<Sgsj> page(SgsjPageParam sgsjPageParam) {
        QueryWrapper<Sgsj> queryWrapper = new QueryWrapper<Sgsj>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgsjPageParam.getSglx())) {
            queryWrapper.lambda().eq(Sgsj::getSglx, sgsjPageParam.getSglx());
        }
        if(ObjectUtil.isNotEmpty(sgsjPageParam.getSgfl())) {
            queryWrapper.lambda().eq(Sgsj::getSgfl, sgsjPageParam.getSgfl());
        }
        if(ObjectUtil.isNotEmpty(sgsjPageParam.getIsAljx())) {
            queryWrapper.lambda().eq(Sgsj::getIsAljx, sgsjPageParam.getIsAljx());
        }
        if(ObjectUtil.isNotEmpty(sgsjPageParam.getSgdj())) {
            queryWrapper.lambda().eq(Sgsj::getSgdj, sgsjPageParam.getSgdj());
        }
        if(ObjectUtil.isAllNotEmpty(sgsjPageParam.getSortField(), sgsjPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgsjPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgsjPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgsjPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Sgsj::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgsjAddParam sgsjAddParam) {
        Sgsj sgsj = BeanUtil.toBean(sgsjAddParam, Sgsj.class);
        this.save(sgsj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgsjEditParam sgsjEditParam) {
        Sgsj sgsj = this.queryEntity(sgsjEditParam.getSgId());
        BeanUtil.copyProperties(sgsjEditParam, sgsj);
        this.updateById(sgsj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgsjIdParam> sgsjIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgsjIdParamList, SgsjIdParam::getSgId));
    }

    @Override
    public Sgsj detail(SgsjIdParam sgsjIdParam) {
        return this.queryEntity(sgsjIdParam.getSgId());
    }

    @Override
    public Sgsj queryEntity(String id) {
        Sgsj sgsj = this.getById(id);
        if(ObjectUtil.isEmpty(sgsj)) {
            throw new CommonException("事故数据不存在，id值为：{}", id);
        }
        return sgsj;
    }
}
