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
package vip.xiaonuo.biz.modular.alsglc.service.impl;

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
import vip.xiaonuo.biz.modular.alsglc.entity.Alsglc;
import vip.xiaonuo.biz.modular.alsglc.mapper.AlsglcMapper;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcAddParam;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcEditParam;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcIdParam;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcPageParam;
import vip.xiaonuo.biz.modular.alsglc.service.AlsglcService;

import java.util.List;

/**
 * 案例事故流程Service接口实现类
 *
 * @author byc
 * @date  2025/01/15 18:31
 **/
@Service
public class AlsglcServiceImpl extends ServiceImpl<AlsglcMapper, Alsglc> implements AlsglcService {

    @Override
    public Page<Alsglc> page(AlsglcPageParam alsglcPageParam) {
        QueryWrapper<Alsglc> queryWrapper = new QueryWrapper<Alsglc>().checkSqlInjection();
        if(ObjectUtil.isAllNotEmpty(alsglcPageParam.getSortField(), alsglcPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(alsglcPageParam.getSortOrder());
            queryWrapper.orderBy(true, alsglcPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(alsglcPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Alsglc::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AlsglcAddParam alsglcAddParam) {
        Alsglc alsglc = BeanUtil.toBean(alsglcAddParam, Alsglc.class);
        this.save(alsglc);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(AlsglcEditParam alsglcEditParam) {
        Alsglc alsglc = this.queryEntity(alsglcEditParam.getSglcId());
        BeanUtil.copyProperties(alsglcEditParam, alsglc);
        this.updateById(alsglc);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<AlsglcIdParam> alsglcIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(alsglcIdParamList, AlsglcIdParam::getSglcId));
    }

    @Override
    public Alsglc detail(AlsglcIdParam alsglcIdParam) {
        return this.queryEntity(alsglcIdParam.getSglcId());
    }

    @Override
    public Alsglc queryEntity(String id) {
        Alsglc alsglc = this.getById(id);
        if(ObjectUtil.isEmpty(alsglc)) {
            throw new CommonException("案例事故流程不存在，id值为：{}", id);
        }
        return alsglc;
    }
}
