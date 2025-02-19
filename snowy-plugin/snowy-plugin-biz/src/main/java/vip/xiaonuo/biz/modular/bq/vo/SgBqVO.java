package vip.xiaonuo.biz.modular.bq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 1. @description:  事故标签VO
 2. @author: zzb
 3. @time: 2025/2/19 
 */
@Getter
@Setter
public class SgBqVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 标签ID */
    @Schema(description = "标签ID")
    private String bqId;

    /** 标签名称 */
    @Schema(description = "标签名称")
    private String name;

    /** 颜色值 */
    @Schema(description = "颜色值")
    private String color;

    /** 排序 */
    @Schema(description = "排序")
    private Integer sortCode;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;

    /** 事故数据ID */
    @Schema(description = "事故数据ID")
    private String sgId;

    /** 是否选中 */
    @Schema(description = "是否选中")
    private boolean checked;
}
