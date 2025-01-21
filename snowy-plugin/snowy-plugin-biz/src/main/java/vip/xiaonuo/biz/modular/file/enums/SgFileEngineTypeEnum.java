package vip.xiaonuo.biz.modular.file.enums;

import lombok.Getter;

/**
 1. @description:  文件存储引擎类型枚举
 2. @author: zzb
 3. @time: 2025/1/20 
 */
@Getter
public enum SgFileEngineTypeEnum {


    /** 本地 */
    LOCAL("LOCAL"),

    /** 阿里云 */
    ALIYUN("ALIYUN"),

    /** 腾讯云 */
    TENCENT("TENCENT"),

    /** MINIO */
    MINIO("MINIO");

    private final String value;

    SgFileEngineTypeEnum(String value) {
        this.value = value;
    }
}
