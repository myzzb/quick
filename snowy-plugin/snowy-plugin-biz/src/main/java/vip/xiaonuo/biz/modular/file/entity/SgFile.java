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
package vip.xiaonuo.biz.modular.file.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 文件资源实体
 *
 * @author zzb
 * @date  2025/01/20 18:03
 **/
@Getter
@Setter
@TableName("sg_file")
public class SgFile {

    /** ID */
    @TableId
    @Schema(description = "ID")
    private String id;

    /** 存储引擎 */
    @Schema(description = "存储引擎")
    private String engine;

    /** 存储桶 */
    @Schema(description = "存储桶")
    private String bucket;

    /** 文件类型ID */
    @Schema(description = "文件类型ID")
    private String fileTypeId;

    /** 文件类型名称 */
    @Schema(description = "文件类型名称")
    @TableField(exist = false)
    private String fileTypeName;

    /** 文件名称 */
    @Schema(description = "文件名称")
    private String name;

    /** 文件后缀 */
    @Schema(description = "文件后缀")
    private String suffix;

    /** 文件大小kb */
    @Schema(description = "文件大小kb")
    private Long sizeKb;

    /** 文件大小（格式化后） */
    @Schema(description = "文件大小（格式化后）")
    private String sizeInfo;

    /** 文件的对象名（唯一名称） */
    @Schema(description = "文件的对象名（唯一名称）")
    private String objName;

    /** 文件存储路径 */
    @Schema(description = "文件存储路径")
    private String storagePath;

    /** 文件下载路径 */
    @Schema(description = "文件下载路径")
    private String downloadPath;

    /** 图片缩略图 */
    @Schema(description = "图片缩略图")
    private String thumbnail;

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

    /** 修改时间 */
    @Schema(description = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /** 修改用户 */
    @Schema(description = "修改用户")
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;
}
