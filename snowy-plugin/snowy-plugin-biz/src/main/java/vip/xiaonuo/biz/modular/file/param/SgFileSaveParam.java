package vip.xiaonuo.biz.modular.file.param;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 1. @description:  文件资源保存参数
 2. @author: zzb
 3. @time: 2025/2/20 
 */
@Getter
@Setter
public class SgFileSaveParam {

    /** 文件资源ID */
    @Schema(description = "ID")
    private String id;

    /** 文件类型ID */
    @Schema(description = "文件类型ID")
    private String fileTypeId;
}
