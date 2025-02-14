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
package vip.xiaonuo.sys.modular.sgfl.service.impl;

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
import vip.xiaonuo.sys.modular.sgfl.entity.SgFl;
import vip.xiaonuo.sys.modular.sgfl.mapper.SgFlMapper;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlAddParam;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlEditParam;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlIdParam;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlPageParam;
import vip.xiaonuo.sys.modular.sgfl.service.SgFlService;

import java.util.List;

/**
 * 事故分类Service接口实现类
 *
 * @author zzb
 * @date  2025/02/12 14:33
 **/
@Service
public class SgFlServiceImpl extends ServiceImpl<SgFlMapper, SgFl> implements SgFlService {

    @Override
    public Page<SgFl> page(SgFlPageParam sgFlPageParam) {
        QueryWrapper<SgFl> queryWrapper = new QueryWrapper<SgFl>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgFlPageParam.getName())) {
            queryWrapper.lambda().like(SgFl::getName, sgFlPageParam.getName());
        }
        if(ObjectUtil.isAllNotEmpty(sgFlPageParam.getSortField(), sgFlPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgFlPageParam.getSortOrder());
            queryWrapper.orderBy(true, sgFlPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgFlPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgFl::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgFlAddParam sgFlAddParam) {
        SgFl sgFl = BeanUtil.toBean(sgFlAddParam, SgFl.class);
        this.save(sgFl);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgFlEditParam sgFlEditParam) {
        SgFl sgFl = this.queryEntity(sgFlEditParam.getId());
        BeanUtil.copyProperties(sgFlEditParam, sgFl);
        this.updateById(sgFl);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgFlIdParam> sgFlIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgFlIdParamList, SgFlIdParam::getId));
    }

    @Override
    public SgFl detail(SgFlIdParam sgFlIdParam) {
        return this.queryEntity(sgFlIdParam.getId());
    }

    @Override
    public SgFl queryEntity(String id) {
        SgFl sgFl = this.getById(id);
        if(ObjectUtil.isEmpty(sgFl)) {
            throw new CommonException("事故分类不存在，id值为：{}", id);
        }
        return sgFl;
    }
}
