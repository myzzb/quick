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
package vip.xiaonuo.biz.modular.zsk.controller;

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
import vip.xiaonuo.biz.modular.zsk.entity.Zsk;
import vip.xiaonuo.biz.modular.zsk.param.ZskAddParam;
import vip.xiaonuo.biz.modular.zsk.param.ZskIdParam;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.biz.modular.zsk.enums.ZskFileEngineTypeEnum;
import vip.xiaonuo.biz.modular.zsk.param.ZskEditParam;
import vip.xiaonuo.biz.modular.zsk.param.ZskPageParam;
import vip.xiaonuo.biz.modular.zsk.service.ZskService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.io.IOException;
import java.util.List;

/**
 * 知识库管理控制器
 *
 * @author zzb
 * @date  2025/02/13 10:58
 */
@Tag(name = "知识库管理控制器")
@RestController
@Validated
public class ZskController {

    @Resource
    private ZskService zskService;


    @Operation(summary = "下载文件")
    @CommonLog("下载文件")
    @GetMapping(value = "/biz/zsk/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void download(@Valid ZskIdParam zskIdParam, HttpServletResponse response) throws IOException {
        zskService.download(zskIdParam, response);
    }

    @Operation(summary = "知识库上传本地文件返回url")
    @CommonLog("知识库上传本地文件返回url")
    @PostMapping("/biz/zsk/zskUploadLocalReturnUrl")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "File", name = "file", value = "上传的文件", required = true),
            @ApiImplicitParam(dataType = "string", name = "ZskFlId", value = "知识库分类ID", required = true)
    })
    public CommonResult<String> uploadLocalReturnUrl(@RequestPart("file") MultipartFile file, String fileTypeId) {
        return CommonResult.data(zskService.uploadReturnUrl(ZskFileEngineTypeEnum.LOCAL.getValue(), file, fileTypeId));
    }
    /**
     * 获取知识库管理分页
     *
     * @author zzb
     * @date  2025/02/13 10:58
     */
    @Operation(summary = "获取知识库管理分页")
    @SaCheckPermission("/biz/zsk/page")
    @GetMapping("/biz/zsk/page")
    public CommonResult<Page<Zsk>> page(ZskPageParam zskPageParam) {
        return CommonResult.data(zskService.page(zskPageParam));
    }

    /**
     * 添加知识库管理
     *
     * @author zzb
     * @date  2025/02/13 10:58
     */
    @Operation(summary = "添加知识库管理")
    @CommonLog("添加知识库管理")
    @SaCheckPermission("/biz/zsk/add")
    @PostMapping("/biz/zsk/add")
    public CommonResult<String> add(@RequestBody @Valid ZskAddParam zskAddParam) {
        zskService.add(zskAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑知识库管理
     *
     * @author zzb
     * @date  2025/02/13 10:58
     */
    @Operation(summary = "编辑知识库管理")
    @CommonLog("编辑知识库管理")
    @SaCheckPermission("/biz/zsk/edit")
    @PostMapping("/biz/zsk/edit")
    public CommonResult<String> edit(@RequestBody @Valid ZskEditParam zskEditParam) {
        zskService.edit(zskEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除知识库管理
     *
     * @author zzb
     * @date  2025/02/13 10:58
     */
    @Operation(summary = "删除知识库管理")
    @CommonLog("删除知识库管理")
    @SaCheckPermission("/biz/zsk/delete")
    @PostMapping("/biz/zsk/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<ZskIdParam> zskIdParamList) {
        zskService.delete(zskIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取知识库管理详情
     *
     * @author zzb
     * @date  2025/02/13 10:58
     */
    @Operation(summary = "获取知识库管理详情")
    @SaCheckPermission("/biz/zsk/detail")
    @GetMapping("/biz/zsk/detail")
    public CommonResult<Zsk> detail(@Valid ZskIdParam zskIdParam) {
        return CommonResult.data(zskService.detail(zskIdParam));
    }
}
