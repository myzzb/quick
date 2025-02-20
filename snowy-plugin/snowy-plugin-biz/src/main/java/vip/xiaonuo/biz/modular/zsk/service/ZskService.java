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
package vip.xiaonuo.biz.modular.zsk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import vip.xiaonuo.biz.modular.zsk.entity.Zsk;
import vip.xiaonuo.biz.modular.zsk.param.ZskAddParam;
import vip.xiaonuo.biz.modular.zsk.param.ZskEditParam;
import vip.xiaonuo.biz.modular.zsk.param.ZskIdParam;
import vip.xiaonuo.biz.modular.zsk.param.ZskPageParam;
import vip.xiaonuo.biz.modular.zsk.param.ZskSaveParam;

import java.io.IOException;
import java.util.List;

/**
 * 知识库管理Service接口
 *
 * @author zzb
 * @date  2025/02/13 10:58
 **/
public interface ZskService extends IService<Zsk> {

    /**
    * @description MultipartFile文件上传，返回文件id
    * @return java.lang.String
    * @author zzb
    * @date 2025/2/20 17:59
    **/
    String uploadReturnId(String engine, MultipartFile file);
    void download(ZskIdParam zskIdParam, HttpServletResponse response) throws IOException;
    /**
    * @description 知识库文件上传
    * @return java.lang.String
    * @author zzb
    * @date 2025/2/13 17:30
    **/
    String uploadReturnUrl(String engine, MultipartFile file, String ZskFlId);
    /**
     * 获取知识库管理分页
     *
     * @author zzb
     * @date  2025/02/13 10:58
     */
    Page<Zsk> page(ZskPageParam zskPageParam);

    /**
     * 添加知识库管理
     *
     * @author zzb
     * @date  2025/02/13 10:58
     */
    void add(ZskAddParam zskAddParam);

    void save(ZskSaveParam zskAddParam);
    /**
     * 编辑知识库管理
     *
     * @author zzb
     * @date  2025/02/13 10:58
     */
    void edit(ZskEditParam zskEditParam);

    /**
     * 删除知识库管理
     *
     * @author zzb
     * @date  2025/02/13 10:58
     */
    void delete(List<ZskIdParam> zskIdParamList);

    /**
     * 获取知识库管理详情
     *
     * @author zzb
     * @date  2025/02/13 10:58
     */
    Zsk detail(ZskIdParam zskIdParam);

    /**
     * 获取知识库管理详情
     *
     * @author zzb
     * @date  2025/02/13 10:58
     **/
    Zsk queryEntity(String id);
}
