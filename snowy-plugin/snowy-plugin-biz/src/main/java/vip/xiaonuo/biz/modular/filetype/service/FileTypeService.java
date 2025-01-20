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
package vip.xiaonuo.biz.modular.filetype.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.filetype.entity.FileType;
import vip.xiaonuo.biz.modular.filetype.param.FileTypeAddParam;
import vip.xiaonuo.biz.modular.filetype.param.FileTypeEditParam;
import vip.xiaonuo.biz.modular.filetype.param.FileTypeIdParam;
import vip.xiaonuo.biz.modular.filetype.param.FileTypePageParam;

import java.util.List;

/**
 * 文件类型Service接口
 *
 * @author zzb
 * @date  2025/01/17 15:04
 **/
public interface FileTypeService extends IService<FileType> {

    /**
     * 获取文件类型分页
     *
     * @author zzb
     * @date  2025/01/17 15:04
     */
    Page<FileType> page(FileTypePageParam fileTypePageParam);

    /**
     * 添加文件类型
     *
     * @author zzb
     * @date  2025/01/17 15:04
     */
    void add(FileTypeAddParam fileTypeAddParam);

    /**
     * 编辑文件类型
     *
     * @author zzb
     * @date  2025/01/17 15:04
     */
    void edit(FileTypeEditParam fileTypeEditParam);

    /**
     * 删除文件类型
     *
     * @author zzb
     * @date  2025/01/17 15:04
     */
    void delete(List<FileTypeIdParam> fileTypeIdParamList);

    /**
     * 获取文件类型详情
     *
     * @author zzb
     * @date  2025/01/17 15:04
     */
    FileType detail(FileTypeIdParam fileTypeIdParam);

    /**
     * 获取文件类型详情
     *
     * @author zzb
     * @date  2025/01/17 15:04
     **/
    FileType queryEntity(String id);

    /**
    * @description 获取文件类型树选择器
    * @return java.util.List<cn.hutool.core.lang.tree.Tree<java.lang.String>>
    * @author zzb
    * @date 2025/1/19 18:42
    **/
    List<Tree<String>> fileTypeTreeSelector();
}
