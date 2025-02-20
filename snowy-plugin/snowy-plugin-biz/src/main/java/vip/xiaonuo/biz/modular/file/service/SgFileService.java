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
package vip.xiaonuo.biz.modular.file.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import vip.xiaonuo.biz.modular.file.entity.SgFile;
import vip.xiaonuo.biz.modular.file.param.SgFileAddParam;
import vip.xiaonuo.biz.modular.file.param.SgFileEditParam;
import vip.xiaonuo.biz.modular.file.param.SgFileIdParam;
import vip.xiaonuo.biz.modular.file.param.SgFilePageParam;
import vip.xiaonuo.biz.modular.file.param.SgFileSaveParam;

import java.io.IOException;
import java.util.List;

/**
 * 文件资源Service接口
 *
 * @author zzb
 * @date  2025/01/20 18:03
 **/
public interface SgFileService extends IService<SgFile> {


    /**
    * @description MultipartFile文件上传，返回文件id
    * @return java.lang.String
    * @author zzb
    * @date 2025/2/20 17:33
    **/
    String uploadReturnId(String engine, MultipartFile file);
    void download(SgFileIdParam sgFileIdParam, HttpServletResponse response) throws IOException;
    /**
    * @description MultipartFile文件上传，返回文件Url
    * @param engine 
 * @param file 
    * @return java.lang.String
    * @author zzb
    * @date 2025/1/21 09:44
    **/
    String uploadReturnUrl(String engine, MultipartFile file, String fileTypeId);
    /**
     * 获取文件资源分页
     *
     * @author zzb
     * @date  2025/01/20 18:03
     */
    Page<SgFile> page(SgFilePageParam sgFilePageParam);

    /**
     * 添加文件资源
     *
     * @author zzb
     * @date  2025/01/20 18:03
     */
    void add(SgFileAddParam sgFileAddParam);

    /**
    * @description 保存文件资源
    * @param sgFileSaveParam
    * @author zzb
    * @date 2025/2/20 17:43
    **/
    void save(SgFileSaveParam sgFileSaveParam);
    /**
     * 编辑文件资源
     *
     * @author zzb
     * @date  2025/01/20 18:03
     */
    void edit(SgFileEditParam sgFileEditParam);

    /**
     * 删除文件资源
     *
     * @author zzb
     * @date  2025/01/20 18:03
     */
    void delete(List<SgFileIdParam> sgFileIdParamList);

    /**
     * 获取文件资源详情
     *
     * @author zzb
     * @date  2025/01/20 18:03
     */
    SgFile detail(SgFileIdParam sgFileIdParam);

    /**
     * 获取文件资源详情
     *
     * @author zzb
     * @date  2025/01/20 18:03
     **/
    SgFile queryEntity(String id);
}
