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
package vip.xiaonuo.sys.modular.sgsj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.sys.modular.sgsj.entity.Sgsj;
import vip.xiaonuo.sys.modular.sgsj.param.SgsjAddParam;
import vip.xiaonuo.sys.modular.sgsj.param.SgsjEditParam;
import vip.xiaonuo.sys.modular.sgsj.param.SgsjIdParam;
import vip.xiaonuo.sys.modular.sgsj.param.SgsjPageParam;

import java.util.List;

/**
 * 事故数据信息表Service接口
 *
 * @author zzb
 * @date  2025/02/12 10:16
 **/
public interface SgsjService extends IService<Sgsj> {

    /**
     * 获取事故数据信息表分页
     *
     * @author zzb
     * @date  2025/02/12 10:16
     */
    Page<Sgsj> page(SgsjPageParam sgsjPageParam);

    /**
     * 添加事故数据信息表
     *
     * @author zzb
     * @date  2025/02/12 10:16
     */
    void add(SgsjAddParam sgsjAddParam);

    /**
     * 编辑事故数据信息表
     *
     * @author zzb
     * @date  2025/02/12 10:16
     */
    void edit(SgsjEditParam sgsjEditParam);

    /**
     * 删除事故数据信息表
     *
     * @author zzb
     * @date  2025/02/12 10:16
     */
    void delete(List<SgsjIdParam> sgsjIdParamList);

    /**
     * 获取事故数据信息表详情
     *
     * @author zzb
     * @date  2025/02/12 10:16
     */
    Sgsj detail(SgsjIdParam sgsjIdParam);

    /**
     * 获取事故数据信息表详情
     *
     * @author zzb
     * @date  2025/02/12 10:16
     **/
    Sgsj queryEntity(String id);
}
