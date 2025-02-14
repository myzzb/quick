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
package vip.xiaonuo.sys.modular.zskfl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.sys.modular.zskfl.entity.ZskFl;
import vip.xiaonuo.sys.modular.zskfl.mapper.ZskFlMapper;
import vip.xiaonuo.sys.modular.zskfl.param.ZskFlAddParam;
import vip.xiaonuo.sys.modular.zskfl.param.ZskFlEditParam;
import vip.xiaonuo.sys.modular.zskfl.param.ZskFlIdParam;
import vip.xiaonuo.sys.modular.zskfl.param.ZskFlPageParam;
import vip.xiaonuo.sys.modular.zskfl.service.ZskFlService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 知识库分类Service接口实现类
 *
 * @author zzb
 * @date  2025/02/13 10:42
 **/
@Slf4j
@Service
public class ZskFlServiceImpl extends ServiceImpl<ZskFlMapper, ZskFl> implements ZskFlService {

    @Override
    public Page<List<TreeNode<String>>> page(ZskFlPageParam zskFlPageParam) {
        log.info("select zskTypePage list is begin, param is {}", JSONObject.toJSONString(zskFlPageParam));
        QueryWrapper<ZskFl> queryWrapper = new QueryWrapper<ZskFl>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(zskFlPageParam.getName())) {
            queryWrapper.lambda().like(ZskFl::getName, zskFlPageParam.getName());
        }
        if(ObjectUtil.isNotEmpty(zskFlPageParam.getType())) {
            queryWrapper.lambda().like(ZskFl::getType, zskFlPageParam.getType());
        }
        if(ObjectUtil.isAllNotEmpty(zskFlPageParam.getSortField(), zskFlPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(zskFlPageParam.getSortOrder());
            queryWrapper.orderBy(true, zskFlPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(zskFlPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(ZskFl::getSortCode);
        }
        List<ZskFl> resourceList = this.list(queryWrapper);


        List<TreeNode<String>> treeNodeList = resourceList.stream().map(fileType ->
                        new TreeNode<>(fileType.getZskFlId(), fileType.getPid(),fileType.getName(),fileType.getSortCode()).setExtra(JSONUtil.parseObj(fileType)))
                .collect(Collectors.toList());


        List<Tree<String>> build = TreeUtil.build(treeNodeList, "");
        long total = 0;
        if (build != null) {
            total = build.size();
        }
        Page page = new Page<>(CommonPageRequest.defaultPage().getCurrent(), CommonPageRequest.defaultPage().getSize(), total);
        page.setRecords(build);

        log.info("select zskTypePage list is end, result is {}", JSONObject.toJSONString(page));
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ZskFlAddParam zskFlAddParam) {
        ZskFl zskFl = BeanUtil.toBean(zskFlAddParam, ZskFl.class);
        this.save(zskFl);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(ZskFlEditParam zskFlEditParam) {
        ZskFl zskFl = this.queryEntity(zskFlEditParam.getZskFlId());
        BeanUtil.copyProperties(zskFlEditParam, zskFl);
        this.updateById(zskFl);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<ZskFlIdParam> zskFlIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(zskFlIdParamList, ZskFlIdParam::getZskFlId));
    }

    @Override
    public ZskFl detail(ZskFlIdParam zskFlIdParam) {
        return this.queryEntity(zskFlIdParam.getZskFlId());
    }

    @Override
    public ZskFl queryEntity(String id) {
        ZskFl zskFl = this.getById(id);
        if(ObjectUtil.isEmpty(zskFl)) {
            throw new CommonException("知识库分类不存在，id值为：{}", id);
        }
        return zskFl;
    }

    @Override
    public List<Tree<String>> zskTypeTreeSelector() {
        log.info("zskTypeTreeSelector is begin, no param ");
        LambdaQueryWrapper<ZskFl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<ZskFl> resourceList = this.list(lambdaQueryWrapper);


        List<TreeNode<String>> treeNodeList = resourceList.stream().map(fileType ->
                        new TreeNode<>(fileType.getZskFlId(), fileType.getPid(),fileType.getName(),fileType.getSortCode()).setExtra(JSONUtil.parseObj(fileType)))
                .collect(Collectors.toList());


        List<Tree<String>> build = TreeUtil.build(treeNodeList, "");
        log.info("zskTypeTreeSelector is end, result is {}", JSONObject.toJSONString(build));
        return build;
    }

    @Override
    public List<ZskFl> getOneLevel(String type) {
        log.info("ZskFl getOneLevel is begin, param is {}", JSONObject.toJSONString(type));
        QueryWrapper<ZskFl> queryWrapper = new QueryWrapper<ZskFl>().checkSqlInjection();
        //一级分类 pid为空
        queryWrapper.lambda().eq(ZskFl::getPid, "");
        if (ObjectUtil.isNotEmpty(type)) {
            queryWrapper.lambda().eq(ZskFl::getType, type);
        }
        queryWrapper.lambda().orderByAsc(ZskFl::getSortCode);
        List<ZskFl> list = this.list(queryWrapper);
        log.info("ZskFl getOneLevel is end, result is {}", JSONObject.toJSONString(list));
        return list;

    }
}
