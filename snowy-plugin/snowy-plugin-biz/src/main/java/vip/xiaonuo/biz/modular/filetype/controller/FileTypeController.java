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
package vip.xiaonuo.biz.modular.filetype.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
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
import vip.xiaonuo.biz.modular.filetype.entity.FileType;
import vip.xiaonuo.biz.modular.filetype.param.FileTypeAddParam;
import vip.xiaonuo.biz.modular.filetype.param.FileTypeEditParam;
import vip.xiaonuo.biz.modular.filetype.param.FileTypeIdParam;
import vip.xiaonuo.biz.modular.filetype.param.FileTypePageParam;
import vip.xiaonuo.biz.modular.filetype.service.FileTypeService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 文件类型控制器
 *
 * @author zzb
 * @date  2025/01/17 15:04
 */
@Tag(name = "文件类型控制器")
@RestController
@Validated
public class FileTypeController {

    @Resource
    private FileTypeService fileTypeService;

    /**
     * 获取文件类型分页
     *
     * @author zzb
     * @date  2025/01/17 15:04
     */
    @Operation(summary = "获取文件类型分页")
    @SaCheckPermission("/biz/filetype/page")
    @GetMapping("/biz/filetype/page")
    public CommonResult<Page<FileType>> page(FileTypePageParam fileTypePageParam) {
        return CommonResult.data(fileTypeService.page(fileTypePageParam));
    }

    /**
     * 添加文件类型
     *
     * @author zzb
     * @date  2025/01/17 15:04
     */
    @Operation(summary = "添加文件类型")
    @CommonLog("添加文件类型")
    @SaCheckPermission("/biz/filetype/add")
    @PostMapping("/biz/filetype/add")
    public CommonResult<String> add(@RequestBody @Valid FileTypeAddParam fileTypeAddParam) {
        fileTypeService.add(fileTypeAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑文件类型
     *
     * @author zzb
     * @date  2025/01/17 15:04
     */
    @Operation(summary = "编辑文件类型")
    @CommonLog("编辑文件类型")
    @SaCheckPermission("/biz/filetype/edit")
    @PostMapping("/biz/filetype/edit")
    public CommonResult<String> edit(@RequestBody @Valid FileTypeEditParam fileTypeEditParam) {
        fileTypeService.edit(fileTypeEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除文件类型
     *
     * @author zzb
     * @date  2025/01/17 15:04
     */
    @Operation(summary = "删除文件类型")
    @CommonLog("删除文件类型")
    @SaCheckPermission("/biz/filetype/delete")
    @PostMapping("/biz/filetype/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<FileTypeIdParam> fileTypeIdParamList) {
        fileTypeService.delete(fileTypeIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取文件类型详情
     *
     * @author zzb
     * @date  2025/01/17 15:04
     */
    @Operation(summary = "获取文件类型详情")
    @SaCheckPermission("/biz/filetype/detail")
    @GetMapping("/biz/filetype/detail")
    public CommonResult<FileType> detail(@Valid FileTypeIdParam fileTypeIdParam) {
        return CommonResult.data(fileTypeService.detail(fileTypeIdParam));
    }
}
