package vip.xiaonuo.sys.modular.zsk.enums;

import lombok.Getter;

/**
 1. @description:  知识库文件存储引擎类型枚举
 2. @author: zzb
 3. @time: 2025/2/13 
 */
@Getter
public enum ZskFileEngineTypeEnum {
    /** 本地 */
    LOCAL("LOCAL"),

    /** 阿里云 */
    ALIYUN("ALIYUN"),

    /** 腾讯云 */
    TENCENT("TENCENT"),

    /** MINIO */
    MINIO("MINIO");

    private final String value;

    ZskFileEngineTypeEnum(String value) {
        this.value = value;
    }
}
