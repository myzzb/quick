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
package vip.xiaonuo.biz.modular.question.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.paper.param.SgPaperIdParam;
import vip.xiaonuo.biz.modular.question.entity.SgQuestion;
import vip.xiaonuo.biz.modular.question.param.SgQuestionAddParam;
import vip.xiaonuo.biz.modular.question.param.SgQuestionEditParam;
import vip.xiaonuo.biz.modular.question.param.SgQuestionIdParam;
import vip.xiaonuo.biz.modular.question.param.SgQuestionPageParam;

import java.util.List;

/**
 * 试题Service接口
 *
 * @author byc
 * @date  2025/02/07 17:43
 **/
public interface SgQuestionService extends IService<SgQuestion> {

    /**
     * 获取试题分页
     *
     * @author byc
     * @date  2025/02/07 17:43
     */
    Page<SgQuestion> page(SgQuestionPageParam sgQuestionPageParam);

    /**
     * 添加试题
     *
     * @author byc
     * @date  2025/02/07 17:43
     */
    void add(SgQuestionAddParam sgQuestionAddParam);

    /**
     * 编辑试题
     *
     * @author byc
     * @date  2025/02/07 17:43
     */
    void edit(SgQuestionEditParam sgQuestionEditParam);

    /**
     * 删除试题
     *
     * @author byc
     * @date  2025/02/07 17:43
     */
    void delete(List<SgQuestionIdParam> sgQuestionIdParamList);

    /**
     * 获取试题详情
     *
     * @author byc
     * @date  2025/02/07 17:43
     */
    SgQuestion detail(SgQuestionIdParam sgQuestionIdParam);

    /**
     * 获取试题详情
     *
     * @author byc
     * @date  2025/02/07 17:43
     **/
    SgQuestion queryEntity(String id);

    /**
     * 获取试卷关联的试题列表
     * @param sgPaperIdParam 试卷ID
     * @return List<SgQuestion>
     */
    List<SgQuestion> question(SgPaperIdParam sgPaperIdParam);
}
