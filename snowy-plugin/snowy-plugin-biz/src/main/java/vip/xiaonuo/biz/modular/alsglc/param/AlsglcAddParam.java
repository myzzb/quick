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
package vip.xiaonuo.biz.modular.alsglc.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 案例事故流程添加参数
 *
 * @author byc
 * @date  2025/01/15 18:31
 **/
@Getter
@Setter
public class AlsglcAddParam {

    /** 案例教学ID */
    @Schema(description = "案例教学ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "aljxId不能为空")
    private String aljxId;

    /** 案例教学事故名称 */
    @Schema(description = "案例教学事故名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "sgmc不能为空")
    private String sgmc;

    /** 流程名称 */
    @Schema(description = "流程名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "lcmc不能为空")
    private String lcmc;

    /** 流程内容 */
    @Schema(description = "流程内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "lcnr不能为空")
    private String lcnr;

    /** 流程步骤排序 */
    @Schema(description = "流程步骤排序")
    @NotNull(message = "sortCode不能为空")
    private Integer lcbzSort;


    /** 排序 */
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sortCode;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;

    /** 扩展信息 */
    @Schema(description = "扩展信息")
    private String extJson;

}
