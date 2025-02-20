package vip.xiaonuo.biz.modular.zsk.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 1. @description:  知识库保存参数
 2. @author: zzb
 3. @time: 2025/2/20 
 */
@Getter
@Setter
public class ZskSaveParam {

    /** 知识库ID */
    @Schema(description = "知识库ID")
    private String zskId;

    /** 知识库分类ID */
    @Schema(description = "知识库分类ID")
    private String zskFlId;
}
