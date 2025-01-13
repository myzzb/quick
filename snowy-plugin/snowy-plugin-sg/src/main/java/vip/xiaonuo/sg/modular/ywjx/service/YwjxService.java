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
package vip.xiaonuo.sg.modular.ywjx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.sg.modular.ywjx.entity.Ywjx;
import vip.xiaonuo.sg.modular.ywjx.param.YwjxAddParam;
import vip.xiaonuo.sg.modular.ywjx.param.YwjxEditParam;
import vip.xiaonuo.sg.modular.ywjx.param.YwjxIdParam;
import vip.xiaonuo.sg.modular.ywjx.param.YwjxPageParam;

import java.util.List;

/**
 * 业务教学Service接口
 *
 * @author zzb
 * @date  2025/01/13 09:49
 **/
public interface YwjxService extends IService<Ywjx> {

    /**
     * 获取业务教学分页
     *
     * @author zzb
     * @date  2025/01/13 09:49
     */
    Page<Ywjx> page(YwjxPageParam ywjxPageParam);

    /**
     * 添加业务教学
     *
     * @author zzb
     * @date  2025/01/13 09:49
     */
    void add(YwjxAddParam ywjxAddParam);

    /**
     * 编辑业务教学
     *
     * @author zzb
     * @date  2025/01/13 09:49
     */
    void edit(YwjxEditParam ywjxEditParam);

    /**
     * 删除业务教学
     *
     * @author zzb
     * @date  2025/01/13 09:49
     */
    void delete(List<YwjxIdParam> ywjxIdParamList);

    /**
     * 获取业务教学详情
     *
     * @author zzb
     * @date  2025/01/13 09:49
     */
    Ywjx detail(YwjxIdParam ywjxIdParam);

    /**
     * 获取业务教学详情
     *
     * @author zzb
     * @date  2025/01/13 09:49
     **/
    Ywjx queryEntity(String id);
}
