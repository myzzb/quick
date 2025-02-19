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
package vip.xiaonuo.biz.modular.zskfl.service;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.zskfl.param.ZskFlAddParam;
import vip.xiaonuo.biz.modular.zskfl.param.ZskFlEditParam;
import vip.xiaonuo.biz.modular.zskfl.param.ZskFlIdParam;
import vip.xiaonuo.biz.modular.zskfl.param.ZskFlPageParam;
import vip.xiaonuo.biz.modular.zskfl.entity.ZskFl;

import java.util.List;

/**
 * 知识库分类Service接口
 *
 * @author zzb
 * @date  2025/02/13 10:42
 **/
public interface ZskFlService extends IService<ZskFl> {

    /**
     * 获取知识库分类分页
     *
     * @author zzb
     * @date  2025/02/13 10:42
     */
    Page<List<TreeNode<String>>> page(ZskFlPageParam zskFlPageParam);

    /**
     * 添加知识库分类
     *
     * @author zzb
     * @date  2025/02/13 10:42
     */
    void add(ZskFlAddParam zskFlAddParam);

    /**
     * 编辑知识库分类
     *
     * @author zzb
     * @date  2025/02/13 10:42
     */
    void edit(ZskFlEditParam zskFlEditParam);

    /**
     * 删除知识库分类
     *
     * @author zzb
     * @date  2025/02/13 10:42
     */
    void delete(List<ZskFlIdParam> zskFlIdParamList);

    /**
     * 获取知识库分类详情
     *
     * @author zzb
     * @date  2025/02/13 10:42
     */
    ZskFl detail(ZskFlIdParam zskFlIdParam);

    /**
     * 获取知识库分类详情
     *
     * @author zzb
     * @date  2025/02/13 10:42
     **/
    ZskFl queryEntity(String id);

    /**
    * @description 获取知识库分类树选择器
    * @return java.util.List<cn.hutool.core.lang.tree.Tree<java.lang.String>>
    * @author zzb
    * @date 2025/2/13 11:36
    **/
    List<Tree<String>> zskTypeTreeSelector();

    /**
    * @description 获取知识库一级分类
    * @param type 分类类型(法律法规库/学习知识库)
    * @return java.util.List<vip.xiaonuo.biz.modular.entity.zskfl.ZskFl>
    * @author zzb
    * @date 2025/2/14 15:30
    **/
    List<ZskFl> getOneLevel(String type);
}
