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
package vip.xiaonuo.biz.modular.filetype.service.impl;

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
import vip.xiaonuo.biz.modular.filetype.entity.FileType;
import vip.xiaonuo.biz.modular.filetype.mapper.FileTypeMapper;
import vip.xiaonuo.biz.modular.filetype.param.FileTypeAddParam;
import vip.xiaonuo.biz.modular.filetype.param.FileTypeEditParam;
import vip.xiaonuo.biz.modular.filetype.param.FileTypeIdParam;
import vip.xiaonuo.biz.modular.filetype.param.FileTypePageParam;
import vip.xiaonuo.biz.modular.filetype.service.FileTypeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文件类型Service接口实现类
 *
 * @author zzb
 * @date  2025/01/17 15:04
 **/
@Slf4j
@Service
public class FileTypeServiceImpl extends ServiceImpl<FileTypeMapper, FileType> implements FileTypeService {

    @Override
    public Page<FileType> page(FileTypePageParam fileTypePageParam) {
        QueryWrapper<FileType> queryWrapper = new QueryWrapper<FileType>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(fileTypePageParam.getName())) {
            queryWrapper.lambda().like(FileType::getName, fileTypePageParam.getName());
        }
        if(ObjectUtil.isAllNotEmpty(fileTypePageParam.getSortField(), fileTypePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(fileTypePageParam.getSortOrder());
            queryWrapper.orderBy(true, fileTypePageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(fileTypePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(FileType::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(FileTypeAddParam fileTypeAddParam) {
        FileType fileType = BeanUtil.toBean(fileTypeAddParam, FileType.class);
        this.save(fileType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(FileTypeEditParam fileTypeEditParam) {
        FileType fileType = this.queryEntity(fileTypeEditParam.getId());
        BeanUtil.copyProperties(fileTypeEditParam, fileType);
        this.updateById(fileType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<FileTypeIdParam> fileTypeIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(fileTypeIdParamList, FileTypeIdParam::getId));
    }

    @Override
    public FileType detail(FileTypeIdParam fileTypeIdParam) {
        return this.queryEntity(fileTypeIdParam.getId());
    }

    @Override
    public FileType queryEntity(String id) {
        FileType fileType = this.getById(id);
        if(ObjectUtil.isEmpty(fileType)) {
            throw new CommonException("文件类型不存在，id值为：{}", id);
        }
        return fileType;
    }

    @Override
    public List<Tree<String>> fileTypeTreeSelector() {
        log.info("fileTypeTreeSelector is begin, no param ");
        LambdaQueryWrapper<FileType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<FileType> resourceList = this.list(lambdaQueryWrapper);


        List<TreeNode<String>> treeNodeList = resourceList.stream().map(FileType ->
                        new TreeNode<>(FileType.getId(), FileType.getPid(),
                                FileType.getName(), FileType.getSortCode()).setExtra(JSONUtil.parseObj(FileType)))
                .collect(Collectors.toList());
        return TreeUtil.build(treeNodeList, "0");

        List<Tree<String>> build = TreeUtil.build(treeNodeList, "0");
        log.info("fileTypeTreeSelector is end, result is {}", JSONObject.toJSONString(build));
        return build;
    }
}
