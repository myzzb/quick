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
package vip.xiaonuo.biz.modular.sgfj.service.impl;

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
import vip.xiaonuo.biz.modular.sgfj.entity.Sgfj;
import vip.xiaonuo.biz.modular.sgfj.mapper.SgfjMapper;
import vip.xiaonuo.biz.modular.sgfj.param.SgfjAddParam;
import vip.xiaonuo.biz.modular.sgfj.param.SgfjEditParam;
import vip.xiaonuo.biz.modular.sgfj.param.SgfjIdParam;
import vip.xiaonuo.biz.modular.sgfj.param.SgfjPageParam;
import vip.xiaonuo.biz.modular.sgfj.service.SgfjService;

import java.util.List;

/**
 * 事故附件-待隐藏Service接口实现类
 *
 * @author zzb
 * @date  2025/02/12 15:30
 **/
@Service
public class SgfjServiceImpl extends ServiceImpl<SgfjMapper, Sgfj> implements SgfjService {

    @Override
    public Page<Sgfj> page(SgfjPageParam sgfjPageParam) {
        QueryWrapper<Sgfj> queryWrapper = new QueryWrapper<Sgfj>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgfjPageParam.getSgId())) {
            queryWrapper.lambda().like(Sgfj::getSgId, sgfjPageParam.getSgId());
        }
        if(ObjectUtil.isNotEmpty(sgfjPageParam.getName())) {
            queryWrapper.lambda().like(Sgfj::getName, sgfjPageParam.getName());
        }
        if(ObjectUtil.isAllNotEmpty(sgfjPageParam.getSortField(), sgfjPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgfjPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgfjPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgfjPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Sgfj::getFjId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgfjAddParam sgfjAddParam) {
        Sgfj sgfj = BeanUtil.toBean(sgfjAddParam, Sgfj.class);
        this.save(sgfj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgfjEditParam sgfjEditParam) {
        Sgfj sgfj = this.queryEntity(sgfjEditParam.getFjId());
        BeanUtil.copyProperties(sgfjEditParam, sgfj);
        this.updateById(sgfj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgfjIdParam> sgfjIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgfjIdParamList, SgfjIdParam::getFjId));
    }

    @Override
    public Sgfj detail(SgfjIdParam sgfjIdParam) {
        return this.queryEntity(sgfjIdParam.getFjId());
    }

    @Override
    public Sgfj queryEntity(String id) {
        Sgfj sgfj = this.getById(id);
        if(ObjectUtil.isEmpty(sgfj)) {
            throw new CommonException("事故附件-待隐藏不存在，id值为：{}", id);
        }
        return sgfj;
    }
}
