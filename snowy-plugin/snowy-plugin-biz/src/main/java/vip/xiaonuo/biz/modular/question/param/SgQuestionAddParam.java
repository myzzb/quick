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
package vip.xiaonuo.biz.modular.question.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 试题添加参数
 *
 * @author byc
 * @date  2025/02/07 17:43
 **/
@Getter
@Setter
public class SgQuestionAddParam {

    /** 所属试卷ID(外键) */
    @Schema(description = "所属试卷ID(外键)")
    private String paperId;

    /** 试卷名称 */
    @Schema(description = "试卷名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "paperName不能为空")
    private String paperName;

    /** 试题名称 */
    @Schema(description = "试题名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "questionName不能为空")
    private String questionName;

    /** 试题选项(JSON字符串) */
    @Schema(description = "试题选项(JSON字符串)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "options不能为空")
    private String options;

    /** 分值 */
    @Schema(description = "分值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "score不能为空")
    private Integer score;

    /** 正确答案 */
    @Schema(description = "正确答案", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "answer不能为空")
    private String answer;

    /** 答案解析 */
    @Schema(description = "答案解析")
    private String analysis;

    /** 试题类型 */
    @Schema(description = "试题类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "type不能为空")
    private String type;

    /** 排序 */
    @Schema(description = "排序")
    private Integer sortCode;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;

    /** 扩展信息 */
    @Schema(description = "扩展信息")
    private String extJson;

}
