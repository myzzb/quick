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
package vip.xiaonuo.biz.modular.answerRecord.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 答题记录编辑参数
 *
 * @author byc
 * @date  2025/02/08 15:06
 **/
@Getter
@Setter
public class SgAnswerRecordEditParam {

    /** 答题记录ID(主键) */
    @Schema(description = "答题记录ID(主键)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 答题统计ID(外键) */
    @Schema(description = "答题统计ID(外键)")
    private String statisticsId;

    /** 用户ID */
    @Schema(description = "用户ID")
    private String userId;

    /** 用户姓名 */
    @Schema(description = "用户姓名")
    private String userName;

    /** 试卷ID(外键) */
    @Schema(description = "试卷ID(外键)")
    private String paperId;

    /** 试卷名称 */
    @Schema(description = "试卷名称")
    private String paperName;

    /** 试题ID(外键ID) */
    @Schema(description = "试题ID(外键ID)")
    private String questionId;

    /** 试题名称 */
    @Schema(description = "试题名称")
    private String questionName;

    /** 试题选项(JSON字符串) */
    @Schema(description = "试题选项(JSON字符串)")
    private String options;

    /** 分值 */
    @Schema(description = "分值")
    private Integer score;

    /** 正确答案 */
    @Schema(description = "正确答案")
    private String answer;

    /** 答案解析 */
    @Schema(description = "答案解析")
    private String analysis;

    /** 试题得分 */
    @Schema(description = "试题得分")
    private Integer userScore;

    /** 考生答案 */
    @Schema(description = "考生答案")
    private String userAnswer;

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
