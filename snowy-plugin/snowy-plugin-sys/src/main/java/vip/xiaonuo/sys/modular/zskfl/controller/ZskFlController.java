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
package vip.xiaonuo.sys.modular.zskfl.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.sys.modular.zskfl.entity.ZskFl;
import vip.xiaonuo.sys.modular.zskfl.param.ZskFlAddParam;
import vip.xiaonuo.sys.modular.zskfl.param.ZskFlEditParam;
import vip.xiaonuo.sys.modular.zskfl.param.ZskFlIdParam;
import vip.xiaonuo.sys.modular.zskfl.param.ZskFlPageParam;
import vip.xiaonuo.sys.modular.zskfl.service.ZskFlService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 知识库分类控制器
 *
 * @author zzb
 * @date  2025/02/13 10:42
 */
@Tag(name = "知识库分类控制器")
@RestController
@Validated
public class ZskFlController {

    @Resource
    private ZskFlService zskFlService;

    /**
     * 获取知识库分类分页
     *
     * @author zzb
     * @date  2025/02/13 10:42
     */
    @Operation(summary = "获取知识库分类分页")
    @SaCheckPermission("/sys/zskfl/page")
    @GetMapping("/sys/zskfl/page")
    public CommonResult<Page<List<TreeNode<String>>>> page(ZskFlPageParam zskFlPageParam) {
        return CommonResult.data(zskFlService.page(zskFlPageParam));
    }

    /**
     * 添加知识库分类
     *
     * @author zzb
     * @date  2025/02/13 10:42
     */
    @Operation(summary = "添加知识库分类")
    @CommonLog("添加知识库分类")
    @SaCheckPermission("/sys/zskfl/add")
    @PostMapping("/sys/zskfl/add")
    public CommonResult<String> add(@RequestBody @Valid ZskFlAddParam zskFlAddParam) {
        zskFlService.add(zskFlAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑知识库分类
     *
     * @author zzb
     * @date  2025/02/13 10:42
     */
    @Operation(summary = "编辑知识库分类")
    @CommonLog("编辑知识库分类")
    @SaCheckPermission("/sys/zskfl/edit")
    @PostMapping("/sys/zskfl/edit")
    public CommonResult<String> edit(@RequestBody @Valid ZskFlEditParam zskFlEditParam) {
        zskFlService.edit(zskFlEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除知识库分类
     *
     * @author zzb
     * @date  2025/02/13 10:42
     */
    @Operation(summary = "删除知识库分类")
    @CommonLog("删除知识库分类")
    @SaCheckPermission("/sys/zskfl/delete")
    @PostMapping("/sys/zskfl/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<ZskFlIdParam> zskFlIdParamList) {
        zskFlService.delete(zskFlIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取知识库分类详情
     *
     * @author zzb
     * @date  2025/02/13 10:42
     */
    @Operation(summary = "获取知识库分类详情")
    @SaCheckPermission("/sys/zskfl/detail")
    @GetMapping("/sys/zskfl/detail")
    public CommonResult<ZskFl> detail(@Valid ZskFlIdParam zskFlIdParam) {
        return CommonResult.data(zskFlService.detail(zskFlIdParam));
    }

    @Operation(summary = "获取知识库分类树选择器")
    //@SaCheckPermission("/sys/zskfl/menuTreeSelector")
    @GetMapping("/sys/zskfl/menuTreeSelector")
    public CommonResult<List<Tree<String>>> zskTypeTreeSelector() {
        return CommonResult.data(zskFlService.zskTypeTreeSelector());
    }
}
