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
package vip.xiaonuo.sg.modular.ywjx.service.impl;

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
import vip.xiaonuo.sg.modular.ywjx.entity.Ywjx;
import vip.xiaonuo.sg.modular.ywjx.mapper.YwjxMapper;
import vip.xiaonuo.sg.modular.ywjx.param.YwjxAddParam;
import vip.xiaonuo.sg.modular.ywjx.param.YwjxEditParam;
import vip.xiaonuo.sg.modular.ywjx.param.YwjxIdParam;
import vip.xiaonuo.sg.modular.ywjx.param.YwjxPageParam;
import vip.xiaonuo.sg.modular.ywjx.service.YwjxService;

import java.util.List;

/**
 * 业务教学Service接口实现类
 *
 * @author zzb
 * @date  2025/01/13 09:49
 **/
@Service
public class YwjxServiceImpl extends ServiceImpl<YwjxMapper, Ywjx> implements YwjxService {

    @Override
    public Page<Ywjx> page(YwjxPageParam ywjxPageParam) {
        QueryWrapper<Ywjx> queryWrapper = new QueryWrapper<Ywjx>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(ywjxPageParam.getSgfl())) {
            queryWrapper.lambda().eq(Ywjx::getSgfl, ywjxPageParam.getSgfl());
        }
        if(ObjectUtil.isNotEmpty(ywjxPageParam.getSgdw())) {
            queryWrapper.lambda().like(Ywjx::getSgdw, ywjxPageParam.getSgdw());
        }
        if(ObjectUtil.isNotEmpty(ywjxPageParam.getSgdj())) {
            queryWrapper.lambda().eq(Ywjx::getSgdj, ywjxPageParam.getSgdj());
        }
        if(ObjectUtil.isAllNotEmpty(ywjxPageParam.getSortField(), ywjxPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(ywjxPageParam.getSortOrder());
            queryWrapper.orderBy(true, ywjxPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(ywjxPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Ywjx::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(YwjxAddParam ywjxAddParam) {
        Ywjx ywjx = BeanUtil.toBean(ywjxAddParam, Ywjx.class);
        this.save(ywjx);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(YwjxEditParam ywjxEditParam) {
        Ywjx ywjx = this.queryEntity(ywjxEditParam.getYwjxId());
        BeanUtil.copyProperties(ywjxEditParam, ywjx);
        this.updateById(ywjx);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<YwjxIdParam> ywjxIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(ywjxIdParamList, YwjxIdParam::getYwjxId));
    }

    @Override
    public Ywjx detail(YwjxIdParam ywjxIdParam) {
        return this.queryEntity(ywjxIdParam.getYwjxId());
    }

    @Override
    public Ywjx queryEntity(String id) {
        Ywjx ywjx = this.getById(id);
        if(ObjectUtil.isEmpty(ywjx)) {
            throw new CommonException("业务教学不存在，id值为：{}", id);
        }
        return ywjx;
    }
}
