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
package vip.xiaonuo.biz.modular.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vip.xiaonuo.biz.modular.file.enums.SgFileEngineTypeEnum;
import vip.xiaonuo.biz.modular.file.util.SgFileLocalUtil;
import vip.xiaonuo.biz.modular.filetype.entity.FileType;
import vip.xiaonuo.biz.modular.filetype.mapper.FileTypeMapper;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.file.entity.SgFile;
import vip.xiaonuo.biz.modular.file.mapper.SgFileMapper;
import vip.xiaonuo.biz.modular.file.param.SgFileAddParam;
import vip.xiaonuo.biz.modular.file.param.SgFileEditParam;
import vip.xiaonuo.biz.modular.file.param.SgFileIdParam;
import vip.xiaonuo.biz.modular.file.param.SgFilePageParam;
import vip.xiaonuo.biz.modular.file.service.SgFileService;
import vip.xiaonuo.common.prop.CommonProperties;
import vip.xiaonuo.common.util.CommonDownloadUtil;
import vip.xiaonuo.common.util.CommonResponseUtil;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 文件资源Service接口实现类
 *
 * @author zzb
 * @date  2025/01/20 18:03
 **/
@Slf4j
@Service
public class SgFileServiceImpl extends ServiceImpl<SgFileMapper, SgFile> implements SgFileService {


    @Resource
    private CommonProperties commonProperties;

    @Resource
    private FileTypeMapper fileTypeMapper;

    @Override
    public void download(SgFileIdParam sgFileIdParam, HttpServletResponse response) throws IOException {
        log.info("download is begin, param is {}", JSONObject.toJSONString(sgFileIdParam));
        SgFile sgFile;
        try {
            sgFile = this.queryEntity(sgFileIdParam.getId());
        } catch (Exception e) {
            CommonResponseUtil.renderError(response, e.getMessage());
            return;
        }
        if(!sgFile.getEngine().equals(SgFileEngineTypeEnum.LOCAL.getValue())) {
            CommonResponseUtil.renderError(response, "非本地文件不支持此方式下载，id值为：" + sgFile.getId());
            return;
        }
        File file = FileUtil.file(sgFile.getStoragePath());
        if(!FileUtil.exist(file)) {
            CommonResponseUtil.renderError(response, "找不到存储的文件，id值为：" + sgFile.getId());
            return;
        }
        CommonDownloadUtil.download(sgFile.getName(), IoUtil.readBytes(FileUtil.getInputStream(file)), response);
        log.info("download is end, not result ");
    }

    @Override
    public String uploadReturnUrl(String engine, MultipartFile file, String fileTypeId) {
        return this.storageFile(engine, file, false,fileTypeId);
    }


    /**
    * @description 存储文件
    * @param engine
    * @param file
    * @param returnFileId
    * @return java.lang.String
    * @author zzb
    * @date 2025/1/20 18:23
    **/
    private String storageFile(String engine, MultipartFile file, boolean returnFileId, String fileTypeId) {

        // 如果引擎为空，默认使用本地
        if(ObjectUtil.isEmpty(engine)) {
            engine = SgFileEngineTypeEnum.LOCAL.getValue();
        }

        // 生成id
        String fileId = IdWorker.getIdStr();

        // 存储桶名称
        String bucketName = "defaultBucketName";

        // 定义存储的url，本地文件返回文件实际路径，其他引擎返回网络地址
        String storageUrl = null;

        // 根据引擎类型执行不同方法
        if(engine.equals(SgFileEngineTypeEnum.LOCAL.getValue())) {

            // 使用固定名称defaultBucketName
            bucketName = "defaultBucketName";
            storageUrl = SgFileLocalUtil.storageFileWithReturnUrl(bucketName, genFileKey(fileId, file), file);
        } else if(engine.equals(SgFileEngineTypeEnum.ALIYUN.getValue())) {

            // 使用阿里云默认配置的bucketName
            // bucketName = SgFileLocalUtil.getDefaultBucketName();
            // storageUrl = DevFileAliyunUtil.storageFileWithReturnUrl(bucketName, genFileKey(fileId, file), file);
        } else if(engine.equals(SgFileEngineTypeEnum.TENCENT.getValue())) {

            // 使用腾讯云默认配置的bucketName
            // bucketName = DevFileTencentUtil.getDefaultBucketName();
            // storageUrl = DevFileTencentUtil.storageFileWithReturnUrl(bucketName, genFileKey(fileId, file), file);
        } else if(engine.equals(SgFileEngineTypeEnum.MINIO.getValue())) {

            // 使用MINIO默认配置的bucketName
            // bucketName = DevFileMinIoUtil.getDefaultBucketName();
            // storageUrl = DevFileMinIoUtil.storageFileWithReturnUrl(bucketName, genFileKey(fileId, file), file);
        } else {
            throw new CommonException("不支持的文件引擎：{}", engine);
        }

        // 将文件信息保存到数据库
        SgFile sgFile = new SgFile();

        // 设置文件id
        sgFile.setId(fileId);
        // 文件类型
        sgFile.setFileTypeId(fileTypeId);

        // 设置存储引擎类型
        sgFile.setEngine(engine);
        sgFile.setBucket(bucketName);
        sgFile.setName(file.getOriginalFilename());
        String suffix = ObjectUtil.isNotEmpty(file.getOriginalFilename())?StrUtil.subAfter(file.getOriginalFilename(),
                StrUtil.DOT, true):null;
        sgFile.setSuffix(suffix);
        sgFile.setSizeKb(Long.valueOf(Convert.toStr(NumberUtil.div(new BigDecimal(file.getSize()), BigDecimal.valueOf(1024))
                .setScale(0, RoundingMode.HALF_UP))));
        sgFile.setSizeInfo(FileUtil.readableFileSize(file.getSize()));
        sgFile.setObjName(ObjectUtil.isNotEmpty(sgFile.getSuffix())?fileId + StrUtil.DOT + sgFile.getSuffix():null);
        // 如果是图片，则压缩生成缩略图
        if(ObjectUtil.isNotEmpty(suffix)) {
            if(isPic(suffix)) {
                try {
                    sgFile.setThumbnail(ImgUtil.toBase64DataUri(ImgUtil.scale(ImgUtil.toImage(file.getBytes()),
                            100, 100, null), suffix));
                } catch (Exception ignored) {
                }
            }
        }
        // 存储路径
        sgFile.setStoragePath(storageUrl);

        // 定义下载地址
        String downloadUrl;

        // 下载路径，注意：本地文件下载地址设置为下载接口地址 + 文件id
        if(engine.equals(SgFileEngineTypeEnum.LOCAL.getValue())) {
            String apiUrl = commonProperties.getBackendUrl();
            if(ObjectUtil.isEmpty(apiUrl)) {
                throw new CommonException("后端域名地址未正确配置：snowy.config.common.backend-url为空");
            }
            downloadUrl= apiUrl + "/sg/file/download?id=" + fileId;
            sgFile.setDownloadPath(downloadUrl);
        } else {
            // 阿里云、腾讯云、MINIO可以直接使用存储地址（公网）作为下载地址
            downloadUrl= storageUrl;
            sgFile.setDownloadPath(sgFile.getStoragePath());
        }

        this.save(sgFile);

        // 如果是返回id则返回文件id
        if(returnFileId) {
            return fileId;
        } else {
            // 否则返回下载地址
            return downloadUrl;
        }
    }

    /**
    * @description 根据文件后缀判断是否图片
    * @author zzb
    * @date 2025/1/20 18:36
    **/
    private static boolean isPic(String fileSuffix) {
        fileSuffix = fileSuffix.toLowerCase();
        return ImgUtil.IMAGE_TYPE_GIF.equals(fileSuffix)
                || ImgUtil.IMAGE_TYPE_JPG.equals(fileSuffix)
                || ImgUtil.IMAGE_TYPE_JPEG.equals(fileSuffix)
                || ImgUtil.IMAGE_TYPE_BMP.equals(fileSuffix)
                || ImgUtil.IMAGE_TYPE_PNG.equals(fileSuffix)
                || ImgUtil.IMAGE_TYPE_PSD.equals(fileSuffix);
    }
    /**
    * @description 生成文件的key，格式如 2021/10/11/1377109572375810050.docx
    * @return java.lang.String
    * @author zzb
    * @date 2025/1/20 18:27
    **/
    public String genFileKey(String fileId, MultipartFile file) {

        // 获取文件原始名称
        String originalFileName = file.getOriginalFilename();

        // 获取文件后缀
        String fileSuffix = FileUtil.getSuffix(originalFileName);

        // 生成文件的对象名称，格式如:1377109572375810050.docx
        String fileObjectName = fileId + StrUtil.DOT + fileSuffix;

        // 获取日期文件夹，格式如，2021/10/11/
        String dateFolderPath = DateUtil.thisYear() + StrUtil.SLASH +
                (DateUtil.thisMonth() + 1) + StrUtil.SLASH +
                DateUtil.thisDayOfMonth() + StrUtil.SLASH;

        // 返回
        return dateFolderPath + fileObjectName;
    }

    @Override
    public Page<SgFile> page(SgFilePageParam sgFilePageParam) {
        log.info("select SgFile page is begin, param is {}", JSONObject.toJSONString(sgFilePageParam));
        QueryWrapper<SgFile> queryWrapper = new QueryWrapper<SgFile>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(sgFilePageParam.getName())) {
            queryWrapper.lambda().like(SgFile::getName, sgFilePageParam.getName());
        }
        if(ObjectUtil.isAllNotEmpty(sgFilePageParam.getSortField(), sgFilePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sgFilePageParam.getSortOrder());
            queryWrapper.orderBy(true, sgFilePageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sgFilePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SgFile::getId);
        }
        Page<SgFile> page = this.page(CommonPageRequest.defaultPage(), queryWrapper);
        page.getRecords().forEach(item -> {
            FileType fileType = fileTypeMapper.selectById(item.getFileTypeId());
            if (ObjectUtil.isNotEmpty(fileType)) {
                item.setFileTypeName(fileType.getName());
            } else {
                item.setFileTypeName("未知");
            }
        });
        log.info("select SgFile page is end, result is {}", JSONObject.toJSONString(page));
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SgFileAddParam sgFileAddParam) {
        SgFile sgFile = BeanUtil.toBean(sgFileAddParam, SgFile.class);
        this.save(sgFile);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SgFileEditParam sgFileEditParam) {
        SgFile sgFile = this.queryEntity(sgFileEditParam.getId());
        BeanUtil.copyProperties(sgFileEditParam, sgFile);
        this.updateById(sgFile);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SgFileIdParam> sgFileIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sgFileIdParamList, SgFileIdParam::getId));
    }

    @Override
    public SgFile detail(SgFileIdParam sgFileIdParam) {
        return this.queryEntity(sgFileIdParam.getId());
    }

    @Override
    public SgFile queryEntity(String id) {
        SgFile sgFile = this.getById(id);
        if(ObjectUtil.isEmpty(sgFile)) {
            throw new CommonException("文件资源不存在，id值为：{}", id);
        }
        return sgFile;
    }
}
