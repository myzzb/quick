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
package vip.xiaonuo.sg.modular.jxalb.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 案例表 实体
 *
 * @author zzb
 * @date  2025/01/08 16:17
 **/
@Getter
@Setter
@TableName("sg_jxalb")
public class SgJxalb {

    /** 教学案例ID */
    @TableId
    @Schema(description = "教学案例ID")
    private String jxalId;

    /** 事故类型 */
    @Schema(description = "事故类型")
    private String sgLx;

    /** 事故分类 */
    @Schema(description = "事故分类")
    private String sgFl;

    /** 事故单位 */
    @Schema(description = "事故单位")
    private String sgDw;

    /** 事故等级 */
    @Schema(description = "事故等级")
    private String sgDj;

    /** 事故代码 */
    @Schema(description = "事故代码")
    private String jxalDm;

    /** 事故发生时间 */
    @Schema(description = "事故发生时间")
    private Date jxalFssj;

    /** 事故标签 */
    @Schema(description = "事故标签")
    private String jxalBq;

    /** 排序 */
    @Schema(description = "排序")
    private Integer sortCode;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;

    /** 扩展信息 */
    @Schema(description = "扩展信息")
    private String extJson;

    /** 删除标志 */
    @Schema(description = "删除标志")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /** 创建时间 */
    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 创建用户 */
    @Schema(description = "创建用户")
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** 更新时间 */
    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /** 更新用户 */
    @Schema(description = "更新用户")
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;
}
