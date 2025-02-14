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
package vip.xiaonuo.sys.modular.sgfl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.sys.modular.sgfl.entity.SgFl;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlAddParam;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlEditParam;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlIdParam;
import vip.xiaonuo.sys.modular.sgfl.param.SgFlPageParam;

import java.util.List;

/**
 * 事故分类Service接口
 *
 * @author zzb
 * @date  2025/02/12 14:33
 **/
public interface SgFlService extends IService<SgFl> {

    /**
     * 获取事故分类分页
     *
     * @author zzb
     * @date  2025/02/12 14:33
     */
    Page<SgFl> page(SgFlPageParam sgFlPageParam);

    /**
     * 添加事故分类
     *
     * @author zzb
     * @date  2025/02/12 14:33
     */
    void add(SgFlAddParam sgFlAddParam);

    /**
     * 编辑事故分类
     *
     * @author zzb
     * @date  2025/02/12 14:33
     */
    void edit(SgFlEditParam sgFlEditParam);

    /**
     * 删除事故分类
     *
     * @author zzb
     * @date  2025/02/12 14:33
     */
    void delete(List<SgFlIdParam> sgFlIdParamList);

    /**
     * 获取事故分类详情
     *
     * @author zzb
     * @date  2025/02/12 14:33
     */
    SgFl detail(SgFlIdParam sgFlIdParam);

    /**
     * 获取事故分类详情
     *
     * @author zzb
     * @date  2025/02/12 14:33
     **/
    SgFl queryEntity(String id);
}
