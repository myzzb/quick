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
package vip.xiaonuo.sys.modular.sgsj.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 事故数据信息表实体
 *
 * @author zzb
 * @date  2025/02/12 10:16
 **/
@Getter
@Setter
@TableName("sg_sgsj")
public class Sgsj {

    /** 事故数据ID */
    @TableId
    @Schema(description = "事故数据ID")
    private String sgId;

    /** 事故类型(例：火灾、地震、洪涝) */
    @Schema(description = "事故类型(例：火灾、地震、洪涝)")
    private String sglx;

    /** 事故分类ID */
    @Schema(description = "事故分类ID")
    private String sgflId;

    /** 事故名称 */
    @Schema(description = "事故名称")
    private String sgmc;

    /** 案例教学数据 */
    @Schema(description = "案例教学数据")
    private String isAljx;

    /** 事故单位 */
    @Schema(description = "事故单位")
    private String sgdw;

    /** 事故地点 */
    @Schema(description = "事故地点")
    private String sgdd;

    /** 事故地点经度 */
    @Schema(description = "事故地点经度")
    private String sgddJ;

    /** 事故地点纬度 */
    @Schema(description = "事故地点纬度")
    private String sgddW;

    /** 事故等级 */
    @Schema(description = "事故等级")
    private String sgdj;

    /** 事故级别(I II III) */
    @Schema(description = "事故级别(I II III)")
    private String sgjb;

    /** 事故代码/事故编码 */
    @Schema(description = "事故代码/事故编码")
    private String sgdm;

    /** 事故发生时间 */
    @Schema(description = "事故发生时间")
    private String sgfssj;

    /** 事故报告UUID */
    @Schema(description = "事故报告UUID")
    private String sgbgUuid;

    /** 事故报告名称 */
    @Schema(description = "事故报告名称")
    private String sgbgName;

    /** 事故致因分析 */
    @Schema(description = "事故致因分析")
    private String sgzyfx;

    /** 影像资料 */
    @Schema(description = "影像资料")
    private String imageFile;

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
