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
package vip.xiaonuo.biz.modular.sgfj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.sgfj.param.SgfjEditParam;
import vip.xiaonuo.biz.modular.sgfj.entity.Sgfj;
import vip.xiaonuo.biz.modular.sgfj.param.SgfjAddParam;
import vip.xiaonuo.biz.modular.sgfj.param.SgfjIdParam;
import vip.xiaonuo.biz.modular.sgfj.param.SgfjPageParam;

import java.util.List;

/**
 * 事故附件-待隐藏Service接口
 *
 * @author zzb
 * @date  2025/02/12 15:30
 **/
public interface SgfjService extends IService<Sgfj> {

    /**
     * 获取事故附件-待隐藏分页
     *
     * @author zzb
     * @date  2025/02/12 15:30
     */
    Page<Sgfj> page(SgfjPageParam sgfjPageParam);

    /**
     * 添加事故附件-待隐藏
     *
     * @author zzb
     * @date  2025/02/12 15:30
     */
    void add(SgfjAddParam sgfjAddParam);

    /**
     * 编辑事故附件-待隐藏
     *
     * @author zzb
     * @date  2025/02/12 15:30
     */
    void edit(SgfjEditParam sgfjEditParam);

    /**
     * 删除事故附件-待隐藏
     *
     * @author zzb
     * @date  2025/02/12 15:30
     */
    void delete(List<SgfjIdParam> sgfjIdParamList);

    /**
     * 获取事故附件-待隐藏详情
     *
     * @author zzb
     * @date  2025/02/12 15:30
     */
    Sgfj detail(SgfjIdParam sgfjIdParam);

    /**
     * 获取事故附件-待隐藏详情
     *
     * @author zzb
     * @date  2025/02/12 15:30
     **/
    Sgfj queryEntity(String id);
}
