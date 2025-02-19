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
package vip.xiaonuo.biz.modular.sgsjlc.service.impl;

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
import vip.xiaonuo.biz.modular.sgsjlc.entity.SgsjLc;
import vip.xiaonuo.biz.modular.sgsjlc.mapper.SgsjLcMapper;
import vip.xiaonuo.biz.modular.sgsjlc.param.SgsjLcAddParam;
import vip.xiaonuo.biz.modular.sgsjlc.param.SgsjLcEditParam;
import vip.xiaonuo.biz.modular.sgsjlc.param.SgsjLcIdParam;
import vip.xiaonuo.biz.modular.sgsjlc.param.SgsjLcPageParam;
import vip.xiaonuo.biz.modular.sgsjlc.service.SgsjLcService;

import java.util.List;

/**
 * 事故数据流程信息Service接口实现类
 *
 * @author zzb
 * @date  2025/02/18 10:46
 **/
@Service
public class SgsjLcServiceImpl extends ServiceImpl<SgsjLcMapper, SgsjLc> implements SgsjLcService {

    @Override
    public Page<SgsjLc> page(SgsjLcPageParam sgsjLcPageParam) {
        QueryWrapper<SgsjLc> queryWrapper = new QueryWrapper<SgsjLc>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgsjLcPageParam.getSgmc())) {
            queryWrapper.lambda().like(SgsjLc::getSgmc, sgsjLcPageParam.getSgmc());
        }
        if(ObjectUtil.isNotEmpty(sgsjLcPageParam.getLcmc())) {
            queryWrapper.lambda().like(SgsjLc::getLcmc, sgsjLcPageParam.getLcmc());
        }
        if(ObjectUtil.isAllNotEmpty(sgsjLcPageParam.getSortField(), sgsjLcPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgsjLcPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgsjLcPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgsjLcPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgsjLc::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgsjLcAddParam sgsjLcAddParam) {
        SgsjLc sgsjLc = BeanUtil.toBean(sgsjLcAddParam, SgsjLc.class);
        this.save(sgsjLc);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgsjLcEditParam sgsjLcEditParam) {
        SgsjLc sgsjLc = this.queryEntity(sgsjLcEditParam.getSglcId());
        BeanUtil.copyProperties(sgsjLcEditParam, sgsjLc);
        this.updateById(sgsjLc);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgsjLcIdParam> sgsjLcIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgsjLcIdParamList, SgsjLcIdParam::getSglcId));
    }

    @Override
    public SgsjLc detail(SgsjLcIdParam sgsjLcIdParam) {
        return this.queryEntity(sgsjLcIdParam.getSglcId());
    }

    @Override
    public SgsjLc queryEntity(String id) {
        SgsjLc sgsjLc = this.getById(id);
        if(ObjectUtil.isEmpty(sgsjLc)) {
            throw new CommonException("事故数据流程信息不存在，id值为：{}", id);
        }
        return sgsjLc;
    }
}
