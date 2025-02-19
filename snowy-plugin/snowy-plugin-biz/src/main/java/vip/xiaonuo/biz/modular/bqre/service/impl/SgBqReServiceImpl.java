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
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.biz.modular.bq.entity.SgBq;
import vip.xiaonuo.biz.modular.bq.mapper.SgBqMapper;
import vip.xiaonuo.biz.modular.bq.vo.SgBqVO;
import vip.xiaonuo.biz.modular.bqre.vo.SgBqReVO;
import vip.xiaonuo.biz.modular.sgsj.param.SgsjIdParam;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 事故标签关联Service接口实现类
 *
 * @author zzb
 * @date  2025/02/12 15:01
 **/
@Slf4j
@Service
public class SgBqReServiceImpl extends ServiceImpl<SgBqReMapper, SgBqRe> implements SgBqReService {

    @Resource
    private SgBqMapper sgBqMapper;
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

    @Override
    public List<SgBqVO> getBqReVOBySgId(SgsjIdParam sgsjIdParam) {
        log.info("SgBqReVO getBqReVOBySgId is begin, param sgsjIdParam is {}", JSONObject.toJSONString(sgsjIdParam));
        // 1.先获取到所有的标签
        List<SgBq> sgBqList = sgBqMapper.selectList(new QueryWrapper<SgBq>().checkSqlInjection().lambda().eq(SgBq::getDeleteFlag, false));

        // 2.获取到此事故所有的关联关系
        List<SgBqRe> sgBqReList = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(sgsjIdParam.getSgId())) {
            sgBqReList = this.list(new QueryWrapper<SgBqRe>().checkSqlInjection().lambda().eq(SgBqRe::getDeleteFlag, false)
                    .eq(SgBqRe::getSgId, sgsjIdParam.getSgId()));
        }
        // 3.将标签和关联关系进行关联
        List<SgBqVO> list = new ArrayList<>();
        for (SgBq sgBq : sgBqList) {
            SgBqVO sgBqVO = new SgBqVO();
            BeanUtil.copyProperties(sgBq, sgBqVO);
            for (SgBqRe sgBqRe : sgBqReList) {
                if (sgBqRe.getBqId().equals(sgBq.getBqId())) {
                    sgBqVO.setChecked(true);
                    sgBqVO.setSgId(sgBqRe.getSgId());
                    break;
                }
            }
            list.add(sgBqVO);
        }
        log.info("SgBqReVO getBqReVOBySgId is end, result is {}", JSONObject.toJSONString(list));
        return list;
    }
}
