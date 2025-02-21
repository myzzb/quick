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
package vip.xiaonuo.biz.modular.paper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.paper.entity.SgPaper;
import vip.xiaonuo.biz.modular.paper.param.SgPaperAddParam;
import vip.xiaonuo.biz.modular.paper.param.SgPaperEditParam;
import vip.xiaonuo.biz.modular.paper.param.SgPaperIdParam;
import vip.xiaonuo.biz.modular.paper.param.SgPaperPageParam;
import vip.xiaonuo.biz.modular.paperquestion.param.SgPaperQuestionAddParam;

import java.util.List;

/**
 * 试卷表Service接口
 *
 * @author byc
 * @date  2025/02/07 15:58
 **/
public interface SgPaperService extends IService<SgPaper> {

    /**
     * 获取试卷表分页
     *
     * @author byc
     * @date  2025/02/07 15:58
     */
    Page<SgPaper> page(SgPaperPageParam sgPaperPageParam);


    /**
     * 获取试卷全部列表
     * @return List<SgPaper>
     */
    List<SgPaper> allList();

    /**
     * 添加试卷表
     *
     * @author byc
     * @date  2025/02/07 15:58
     */
    void add(SgPaperAddParam sgPaperAddParam);

    /**
     * 编辑试卷表
     *
     * @author byc
     * @date  2025/02/07 15:58
     */
    void edit(SgPaperEditParam sgPaperEditParam);

    /**
     * 删除试卷表
     *
     * @author byc
     * @date  2025/02/07 15:58
     */
    void delete(List<SgPaperIdParam> sgPaperIdParamList);

    /**
     * 获取试卷表详情
     *
     * @author byc
     * @date  2025/02/07 15:58
     */
    SgPaper detail(SgPaperIdParam sgPaperIdParam);

    /**
     * 获取试卷表详情
     *
     * @author byc
     * @date  2025/02/07 15:58
     **/
    SgPaper queryEntity(String id);

    /**
     * 添加试题
     * @param sgPaperQuestionAddParams 试题列表
     */
    void addQuestions(List<SgPaperQuestionAddParam> sgPaperQuestionAddParams);
}
