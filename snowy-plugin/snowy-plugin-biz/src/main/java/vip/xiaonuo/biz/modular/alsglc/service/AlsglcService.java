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
package vip.xiaonuo.biz.modular.alsglc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.alsglc.entity.Alsglc;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcAddParam;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcEditParam;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcIdParam;
import vip.xiaonuo.biz.modular.alsglc.param.AlsglcPageParam;

import java.util.List;

/**
 * 案例事故流程Service接口
 *
 * @author byc
 * @date  2025/01/15 18:31
 **/
public interface AlsglcService extends IService<Alsglc> {

    /**
     * 获取案例事故流程分页
     *
     * @author byc
     * @date  2025/01/15 18:31
     */
    Page<Alsglc> page(AlsglcPageParam alsglcPageParam);

    /**
     * 添加案例事故流程
     *
     * @author byc
     * @date  2025/01/15 18:31
     */
    void add(AlsglcAddParam alsglcAddParam);

    /**
     * 编辑案例事故流程
     *
     * @author byc
     * @date  2025/01/15 18:31
     */
    void edit(AlsglcEditParam alsglcEditParam);

    /**
     * 删除案例事故流程
     *
     * @author byc
     * @date  2025/01/15 18:31
     */
    void delete(List<AlsglcIdParam> alsglcIdParamList);

    /**
     * 获取案例事故流程详情
     *
     * @author byc
     * @date  2025/01/15 18:31
     */
    Alsglc detail(AlsglcIdParam alsglcIdParam);

    /**
     * 获取案例事故流程详情
     *
     * @author byc
     * @date  2025/01/15 18:31
     **/
    Alsglc queryEntity(String id);
}
