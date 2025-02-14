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
package vip.xiaonuo.sys.modular.bq.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.sys.modular.bq.entity.SgBq;
import vip.xiaonuo.sys.modular.bq.param.SgBqAddParam;
import vip.xiaonuo.sys.modular.bq.param.SgBqEditParam;
import vip.xiaonuo.sys.modular.bq.param.SgBqIdParam;
import vip.xiaonuo.sys.modular.bq.param.SgBqPageParam;

import java.util.List;

/**
 * 标签管理Service接口
 *
 * @author zzb
 * @date  2025/02/12 11:55
 **/
public interface SgBqService extends IService<SgBq> {

    /**
     * 获取标签管理分页
     *
     * @author zzb
     * @date  2025/02/12 11:55
     */
    Page<SgBq> page(SgBqPageParam sgBqPageParam);

    /**
     * 添加标签管理
     *
     * @author zzb
     * @date  2025/02/12 11:55
     */
    void add(SgBqAddParam sgBqAddParam);

    /**
     * 编辑标签管理
     *
     * @author zzb
     * @date  2025/02/12 11:55
     */
    void edit(SgBqEditParam sgBqEditParam);

    /**
     * 删除标签管理
     *
     * @author zzb
     * @date  2025/02/12 11:55
     */
    void delete(List<SgBqIdParam> sgBqIdParamList);

    /**
     * 获取标签管理详情
     *
     * @author zzb
     * @date  2025/02/12 11:55
     */
    SgBq detail(SgBqIdParam sgBqIdParam);

    /**
     * 获取标签管理详情
     *
     * @author zzb
     * @date  2025/02/12 11:55
     **/
    SgBq queryEntity(String id);
}
