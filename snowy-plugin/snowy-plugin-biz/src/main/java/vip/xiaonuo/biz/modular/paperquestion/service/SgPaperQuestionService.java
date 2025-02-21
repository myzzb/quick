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
package vip.xiaonuo.biz.modular.paperquestion.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.paperquestion.entity.SgPaperQuestion;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionAddParam;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionEditParam;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionIdParam;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionPageParam;

import java.util.List;

/**
 * 试卷试题关系表Service接口
 *
 * @author byc
 * @date  2025/02/21 11:35
 **/
public interface SgPaperQuestionService extends IService<SgPaperQuestion> {

    /**
     * 获取试卷试题关系表分页
     *
     * @author byc
     * @date  2025/02/21 11:35
     */
    Page<SgPaperQuestion> page(SgPaperQuestionPageParam sgPaperQuestionPageParam);

    /**
     * 添加试卷试题关系表
     *
     * @author byc
     * @date  2025/02/21 11:35
     */
    void add(SgPaperQuestionAddParam sgPaperQuestionAddParam);

    /**
     * 编辑试卷试题关系表
     *
     * @author byc
     * @date  2025/02/21 11:35
     */
    void edit(SgPaperQuestionEditParam sgPaperQuestionEditParam);

    /**
     * 删除试卷试题关系表
     *
     * @author byc
     * @date  2025/02/21 11:35
     */
    void delete(List<SgPaperQuestionIdParam> sgPaperQuestionIdParamList);

    /**
     * 获取试卷试题关系表详情
     *
     * @author byc
     * @date  2025/02/21 11:35
     */
    SgPaperQuestion detail(SgPaperQuestionIdParam sgPaperQuestionIdParam);

    /**
     * 获取试卷试题关系表详情
     *
     * @author byc
     * @date  2025/02/21 11:35
     **/
    SgPaperQuestion queryEntity(String id);
}
