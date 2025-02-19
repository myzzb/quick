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
package vip.xiaonuo.biz.modular.sgsjlc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.sgsjlc.entity.SgsjLc;
import vip.xiaonuo.biz.modular.sgsjlc.param.SgsjLcAddParam;
import vip.xiaonuo.biz.modular.sgsjlc.param.SgsjLcEditParam;
import vip.xiaonuo.biz.modular.sgsjlc.param.SgsjLcIdParam;
import vip.xiaonuo.biz.modular.sgsjlc.param.SgsjLcPageParam;

import java.util.List;

/**
 * 事故数据流程信息Service接口
 *
 * @author zzb
 * @date  2025/02/18 10:46
 **/
public interface SgsjLcService extends IService<SgsjLc> {

    /**
     * 获取事故数据流程信息分页
     *
     * @author zzb
     * @date  2025/02/18 10:46
     */
    Page<SgsjLc> page(SgsjLcPageParam sgsjLcPageParam);

    /**
     * 添加事故数据流程信息
     *
     * @author zzb
     * @date  2025/02/18 10:46
     */
    void add(SgsjLcAddParam sgsjLcAddParam);

    /**
     * 编辑事故数据流程信息
     *
     * @author zzb
     * @date  2025/02/18 10:46
     */
    void edit(SgsjLcEditParam sgsjLcEditParam);

    /**
     * 删除事故数据流程信息
     *
     * @author zzb
     * @date  2025/02/18 10:46
     */
    void delete(List<SgsjLcIdParam> sgsjLcIdParamList);

    /**
     * 获取事故数据流程信息详情
     *
     * @author zzb
     * @date  2025/02/18 10:46
     */
    SgsjLc detail(SgsjLcIdParam sgsjLcIdParam);

    /**
     * 获取事故数据流程信息详情
     *
     * @author zzb
     * @date  2025/02/18 10:46
     **/
    SgsjLc queryEntity(String id);
}
