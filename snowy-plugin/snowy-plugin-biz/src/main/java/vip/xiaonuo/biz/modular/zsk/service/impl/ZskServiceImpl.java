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
package vip.xiaonuo.biz.modular.zsk.service.impl;

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
import vip.xiaonuo.biz.modular.zsk.param.ZskSaveParam;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.common.prop.CommonProperties;
import vip.xiaonuo.common.util.CommonDownloadUtil;
import vip.xiaonuo.common.util.CommonResponseUtil;
import vip.xiaonuo.biz.modular.zsk.entity.Zsk;
import vip.xiaonuo.biz.modular.zsk.enums.ZskFileEngineTypeEnum;
import vip.xiaonuo.biz.modular.zsk.mapper.ZskMapper;
import vip.xiaonuo.biz.modular.zsk.param.ZskAddParam;
import vip.xiaonuo.biz.modular.zsk.param.ZskEditParam;
import vip.xiaonuo.biz.modular.zsk.param.ZskIdParam;
import vip.xiaonuo.biz.modular.zsk.param.ZskPageParam;
import vip.xiaonuo.biz.modular.zsk.service.ZskService;
import vip.xiaonuo.biz.modular.zsk.util.FileClassifier;
import vip.xiaonuo.biz.modular.zsk.util.ZskFileLocalUtil;
import vip.xiaonuo.biz.modular.zskfl.entity.ZskFl;
import vip.xiaonuo.biz.modular.zskfl.mapper.ZskFlMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 知识库管理Service接口实现类
 *
 * @author zzb
 * @date  2025/02/13 10:58
 **/
@Slf4j
@Service
public class ZskServiceImpl extends ServiceImpl<ZskMapper, Zsk> implements ZskService {

    @Resource
    private CommonProperties commonProperties;

    /**
     * 知识库分类mapper
     **/
    @Resource
    private ZskFlMapper zskFlMapper;

    @Override
    public String uploadReturnId(String engine, MultipartFile file) {
        return this.storageFile(engine, file, true);
    }

    @Override
    public void download(ZskIdParam zskIdParam, HttpServletResponse response) throws IOException {
        log.info("download is begin, param is {}", JSONObject.toJSONString(zskIdParam));
        Zsk zsk;
        try {
            zsk = this.queryEntity(zskIdParam.getZskId());
        } catch (Exception e) {
            CommonResponseUtil.renderError(response, e.getMessage());
            return;
        }
        if(!zsk.getEngine().equals(ZskFileEngineTypeEnum.LOCAL.getValue())) {
            CommonResponseUtil.renderError(response, "非本地文件不支持此方式下载，id值为：" + zsk.getZskId());
            return;
        }
        File file = FileUtil.file(zsk.getStoragePath());
        if(!FileUtil.exist(file)) {
            CommonResponseUtil.renderError(response, "找不到存储的文件，id值为：" + zsk.getZskId());
            return;
        }
        CommonDownloadUtil.download(zsk.getName(), IoUtil.readBytes(FileUtil.getInputStream(file)), response);
        log.info("download is end, not result ");
    }

    @Override
    public String uploadReturnUrl(String engine, MultipartFile file, String ZskFlId) {
        return this.storageFile(engine, file, false);
    }

    private String storageFile(String engine, MultipartFile file, boolean returnFileId) {

        // 如果引擎为空，默认使用本地
        if(ObjectUtil.isEmpty(engine)) {
            engine = ZskFileEngineTypeEnum.LOCAL.getValue();
        }

        // 生成id
        String fileId = IdWorker.getIdStr();

        // 存储桶名称
        String bucketName = "defaultBucketName";

        // 定义存储的url，本地文件返回文件实际路径，其他引擎返回网络地址
        String storageUrl = null;

        // 根据引擎类型执行不同方法
        if(engine.equals(ZskFileEngineTypeEnum.LOCAL.getValue())) {

            // 使用固定名称defaultBucketName
            bucketName = "defaultBucketName";
            storageUrl = ZskFileLocalUtil.storageFileWithReturnUrl(bucketName, genFileKey(fileId, file), file);
        } else if(engine.equals(ZskFileEngineTypeEnum.ALIYUN.getValue())) {

            // 使用阿里云默认配置的bucketName
            // bucketName = SgFileLocalUtil.getDefaultBucketName();
            // storageUrl = DevFileAliyunUtil.storageFileWithReturnUrl(bucketName, genFileKey(fileId, file), file);
        } else if(engine.equals(ZskFileEngineTypeEnum.TENCENT.getValue())) {

            // 使用腾讯云默认配置的bucketName
            // bucketName = DevFileTencentUtil.getDefaultBucketName();
            // storageUrl = DevFileTencentUtil.storageFileWithReturnUrl(bucketName, genFileKey(fileId, file), file);
        } else if(engine.equals(ZskFileEngineTypeEnum.MINIO.getValue())) {

            // 使用MINIO默认配置的bucketName
            // bucketName = DevFileMinIoUtil.getDefaultBucketName();
            // storageUrl = DevFileMinIoUtil.storageFileWithReturnUrl(bucketName, genFileKey(fileId, file), file);
        } else {
            throw new CommonException("不支持的文件引擎：{}", engine);
        }

        // 将文件信息保存到数据库
        Zsk zsk = new Zsk();

        // 设置文件id
        zsk.setZskId(fileId);
        // 文件类型
        //zsk.setZskFlId(ZskFlId);

        // 重新定义文件类型
        String type = FileClassifier.classifyFile(file.getOriginalFilename());
        zsk.setType(type);
        // 设置存储引擎类型
        zsk.setEngine(engine);
        zsk.setBucket(bucketName);
        zsk.setName(file.getOriginalFilename());
        String suffix = ObjectUtil.isNotEmpty(file.getOriginalFilename())?StrUtil.subAfter(file.getOriginalFilename(),
                StrUtil.DOT, true):null;
        zsk.setSuffix(suffix);
        String originalFilename = file.getOriginalFilename();
        String fileNameWithoutSuffix = ObjectUtil.isNotEmpty(originalFilename) && StrUtil.contains(originalFilename, ".")
                ? originalFilename.substring(0, originalFilename.lastIndexOf('.'))
                : originalFilename;
        zsk.setTitle(fileNameWithoutSuffix);
        zsk.setSizeKb(Long.valueOf(Convert.toStr(NumberUtil.div(new BigDecimal(file.getSize()), BigDecimal.valueOf(1024))
                .setScale(0, RoundingMode.HALF_UP))));
        zsk.setSizeInfo(FileUtil.readableFileSize(file.getSize()));
        zsk.setObjName(ObjectUtil.isNotEmpty(zsk.getSuffix())?fileId + StrUtil.DOT + zsk.getSuffix():null);
        // 如果是图片，则压缩生成缩略图
        if(ObjectUtil.isNotEmpty(suffix)) {
            if(isPic(suffix)) {
                try {
                    zsk.setThumbnail(ImgUtil.toBase64DataUri(ImgUtil.scale(ImgUtil.toImage(file.getBytes()),
                            100, 100, null), suffix));
                } catch (Exception ignored) {
                }
            }
        }
        // 存储路径
        zsk.setStoragePath(storageUrl);

        // 定义下载地址
        String downloadUrl;

        // 下载路径，注意：本地文件下载地址设置为下载接口地址 + 文件id
        if(engine.equals(ZskFileEngineTypeEnum.LOCAL.getValue())) {
            String apiUrl = commonProperties.getBackendUrl();
            if(ObjectUtil.isEmpty(apiUrl)) {
                throw new CommonException("后端域名地址未正确配置：snowy.config.common.backend-url为空");
            }
            downloadUrl= apiUrl + "/biz/zsk/download?zskId=" + fileId;
            zsk.setDownloadPath(downloadUrl);
        } else {
            // 阿里云、腾讯云、MINIO可以直接使用存储地址（公网）作为下载地址
            downloadUrl= storageUrl;
            zsk.setDownloadPath(zsk.getStoragePath());
        }

        this.save(zsk);

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
    public Page<Zsk> page(ZskPageParam zskPageParam) {
        QueryWrapper<Zsk> queryWrapper = new QueryWrapper<Zsk>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(zskPageParam.getZskFlId())) {
            queryWrapper.lambda().eq(Zsk::getZskFlId, zskPageParam.getZskFlId());
        }
        if(ObjectUtil.isNotEmpty(zskPageParam.getTitle())) {
            queryWrapper.lambda().like(Zsk::getTitle, zskPageParam.getTitle());
        }
        if(ObjectUtil.isAllNotEmpty(zskPageParam.getSortField(), zskPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(zskPageParam.getSortOrder());
            queryWrapper.orderBy(true, zskPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(zskPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Zsk::getZskId);
        }
        Page<Zsk> page = this.page(CommonPageRequest.defaultPage(), queryWrapper);
        page.getRecords().forEach(item -> {
            ZskFl fileType = zskFlMapper.selectById(item.getZskFlId());
            if (ObjectUtil.isNotEmpty(fileType)) {
                item.setZskFlName(fileType.getName());
            } else {
                item.setZskFlName("未知");
            }
        });
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ZskAddParam zskAddParam) {
        Zsk zsk = BeanUtil.toBean(zskAddParam, Zsk.class);
        this.save(zsk);
    }

    @Override
    public void save(ZskSaveParam zskAddParam) {
        log.info("Zsk save is begin, param is {}", JSONObject.toJSONString(zskAddParam));
        Zsk zsk = this.queryEntity(zskAddParam.getZskId());
        BeanUtil.copyProperties(zskAddParam, zsk);
        this.updateById(zsk);
        log.info("Zsk save is end, result is nothing");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(ZskEditParam zskEditParam) {
        Zsk zsk = this.queryEntity(zskEditParam.getZskId());
        BeanUtil.copyProperties(zskEditParam, zsk);
        this.updateById(zsk);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<ZskIdParam> zskIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(zskIdParamList, ZskIdParam::getZskId));
    }

    @Override
    public Zsk detail(ZskIdParam zskIdParam) {
        return this.queryEntity(zskIdParam.getZskId());
    }

    @Override
    public Zsk queryEntity(String id) {
        Zsk zsk = this.getById(id);
        if(ObjectUtil.isEmpty(zsk)) {
            throw new CommonException("知识库管理不存在，id值为：{}", id);
        }
        return zsk;
    }
}
