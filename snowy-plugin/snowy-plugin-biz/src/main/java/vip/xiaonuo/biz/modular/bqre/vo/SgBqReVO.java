package vip.xiaonuo.biz.modular.bqre.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 1. @description:  事故标签关联VO
 2. @author: zzb
 3. @time: 2025/2/19 
 */
@Getter
@Setter
public class SgBqReVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    @Schema(description = "ID")
    private String id;

    /** 事故数据ID */
    @Schema(description = "事故数据ID")
    private String sgId;

    /** 标签ID */
    @Schema(description = "标签ID")
    private String bqId;

    /** 排序 */
    @Schema(description = "排序")
    private Integer sortCode;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;

    /** 扩展信息 */
    @Schema(description = "扩展信息")
    private String extJson;

    /** 创建时间 */
    @Schema(description = "创建时间")
    private Date createTime;

    /** 是否选中 */
    @Schema(description = "是否选中")
    private Boolean isChecked;
}
