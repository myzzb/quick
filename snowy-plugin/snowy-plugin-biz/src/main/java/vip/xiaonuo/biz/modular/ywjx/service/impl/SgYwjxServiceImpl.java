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
package vip.xiaonuo.biz.modular.ywjx.service.impl;

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
import vip.xiaonuo.biz.modular.ywjx.entity.SgYwjx;
import vip.xiaonuo.biz.modular.ywjx.mapper.SgYwjxMapper;
import vip.xiaonuo.biz.modular.ywjx.param.SgYwjxAddParam;
import vip.xiaonuo.biz.modular.ywjx.param.SgYwjxEditParam;
import vip.xiaonuo.biz.modular.ywjx.param.SgYwjxIdParam;
import vip.xiaonuo.biz.modular.ywjx.param.SgYwjxPageParam;
import vip.xiaonuo.biz.modular.ywjx.service.SgYwjxService;

import java.util.List;

/**
 * 业务教学Service接口实现类
 *
 * @author byc
 * @date  2025/01/22 15:09
 **/
@Service
public class SgYwjxServiceImpl extends ServiceImpl<SgYwjxMapper, SgYwjx> implements SgYwjxService {

    @Override
    public Page<SgYwjx> page(SgYwjxPageParam sgYwjxPageParam) {
        QueryWrapper<SgYwjx> queryWrapper = new QueryWrapper<SgYwjx>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgYwjxPageParam.getSglx())) {
            queryWrapper.lambda().like(SgYwjx::getSglx, sgYwjxPageParam.getSglx());
        }
        if(ObjectUtil.isNotEmpty(sgYwjxPageParam.getSgfl())) {
            queryWrapper.lambda().eq(SgYwjx::getSgfl, sgYwjxPageParam.getSgfl());
        }
        if(ObjectUtil.isNotEmpty(sgYwjxPageParam.getSgdj())) {
            queryWrapper.lambda().eq(SgYwjx::getSgdj, sgYwjxPageParam.getSgdj());
        }
        if(ObjectUtil.isAllNotEmpty(sgYwjxPageParam.getSortField(), sgYwjxPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgYwjxPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgYwjxPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgYwjxPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgYwjx::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgYwjxAddParam sgYwjxAddParam) {
        SgYwjx sgYwjx = BeanUtil.toBean(sgYwjxAddParam, SgYwjx.class);
        this.save(sgYwjx);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgYwjxEditParam sgYwjxEditParam) {
        SgYwjx sgYwjx = this.queryEntity(sgYwjxEditParam.getYwjxId());
        BeanUtil.copyProperties(sgYwjxEditParam, sgYwjx);
        this.updateById(sgYwjx);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgYwjxIdParam> sgYwjxIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgYwjxIdParamList, SgYwjxIdParam::getYwjxId));
    }

    @Override
    public SgYwjx detail(SgYwjxIdParam sgYwjxIdParam) {
        return this.queryEntity(sgYwjxIdParam.getYwjxId());
    }

    @Override
    public SgYwjx queryEntity(String id) {
        SgYwjx sgYwjx = this.getById(id);
        if(ObjectUtil.isEmpty(sgYwjx)) {
            throw new CommonException("业务教学不存在，id值为：{}", id);
        }
        return sgYwjx;
    }
}
