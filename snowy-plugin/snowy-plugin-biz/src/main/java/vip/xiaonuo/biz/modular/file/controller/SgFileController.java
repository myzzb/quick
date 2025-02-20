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
package vip.xiaonuo.biz.modular.file.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vip.xiaonuo.biz.modular.file.enums.SgFileEngineTypeEnum;
import vip.xiaonuo.biz.modular.file.param.SgFileSaveParam;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.biz.modular.file.entity.SgFile;
import vip.xiaonuo.biz.modular.file.param.SgFileAddParam;
import vip.xiaonuo.biz.modular.file.param.SgFileEditParam;
import vip.xiaonuo.biz.modular.file.param.SgFileIdParam;
import vip.xiaonuo.biz.modular.file.param.SgFilePageParam;
import vip.xiaonuo.biz.modular.file.service.SgFileService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.io.IOException;
import java.util.List;

/**
 * 文件资源控制器
 *
 * @author zzb
 * @date  2025/01/20 18:03
 */
@Tag(name = "文件资源控制器")
@RestController
@Validated
public class SgFileController {

    @Resource
    private SgFileService sgFileService;



    @Operation(summary = "上传本地文件返回url")
    @CommonLog("上传本地文件返回url")
    @PostMapping("/biz/file/uploadLocalReturnUrl")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "File", name = "file", value = "上传的文件", required = true),
            @ApiImplicitParam(dataType = "string", name = "fileTypeId", value = "文件资源类型ID", required = true)
    })
    public CommonResult<String> uploadLocalReturnUrl(@RequestPart("file") MultipartFile file, String fileTypeId) {
        return CommonResult.data(sgFileService.uploadReturnUrl(SgFileEngineTypeEnum.LOCAL.getValue(), file, fileTypeId));
    }

    @Operation(summary = "上传本地文件返回id")
    @CommonLog("上传本地文件返回id")
    @PostMapping("/biz/file/uploadLocalReturnId")
    public CommonResult<String> uploadLocalReturnId(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(sgFileService.uploadReturnId(SgFileEngineTypeEnum.LOCAL.getValue(), file));
    }

    @Operation(summary = "保存文件资源")
    @CommonLog("保存文件资源")
    @PostMapping("/biz/file/save")
    public CommonResult<String> save(@RequestBody @Valid SgFileSaveParam sgFileSaveParam) {
        sgFileService.save(sgFileSaveParam);
        return CommonResult.ok();
    }

    @Operation(summary = "下载文件")
    @CommonLog("下载文件")
    @GetMapping(value = "/sg/file/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void download(@Valid SgFileIdParam sgFileIdParam, HttpServletResponse response) throws IOException {
        sgFileService.download(sgFileIdParam, response);
    }

    /**
     * 获取文件资源分页
     *
     * @author zzb
     * @date  2025/01/20 18:03
     */
    @Operation(summary = "获取文件资源分页")
    @SaCheckPermission("/biz/file/page")
    @GetMapping("/biz/file/page")
    public CommonResult<Page<SgFile>> page(SgFilePageParam sgFilePageParam) {
        return CommonResult.data(sgFileService.page(sgFilePageParam));
    }

    /**
     * 添加文件资源
     *
     * @author zzb
     * @date  2025/01/20 18:03
     */
    @Operation(summary = "添加文件资源")
    @CommonLog("添加文件资源")
    @SaCheckPermission("/biz/file/add")
    @PostMapping("/biz/file/add")
    public CommonResult<String> add(@RequestBody @Valid SgFileAddParam sgFileAddParam) {
        sgFileService.add(sgFileAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑文件资源
     *
     * @author zzb
     * @date  2025/01/20 18:03
     */
    @Operation(summary = "编辑文件资源")
    @CommonLog("编辑文件资源")
    @SaCheckPermission("/biz/file/edit")
    @PostMapping("/biz/file/edit")
    public CommonResult<String> edit(@RequestBody @Valid SgFileEditParam sgFileEditParam) {
        sgFileService.edit(sgFileEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除文件资源
     *
     * @author zzb
     * @date  2025/01/20 18:03
     */
    @Operation(summary = "删除文件资源")
    @CommonLog("删除文件资源")
    @SaCheckPermission("/biz/file/delete")
    @PostMapping("/biz/file/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<SgFileIdParam> sgFileIdParamList) {
        sgFileService.delete(sgFileIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取文件资源详情
     *
     * @author zzb
     * @date  2025/01/20 18:03
     */
    @Operation(summary = "获取文件资源详情")
    @SaCheckPermission("/biz/file/detail")
    @GetMapping("/biz/file/detail")
    public CommonResult<SgFile> detail(@Valid SgFileIdParam sgFileIdParam) {
        return CommonResult.data(sgFileService.detail(sgFileIdParam));
    }
}
