package vip.xiaonuo.sys.modular.zsk.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 1. @description:  根据文件后缀名分类文件
 2. @author: zzb
 3. @time: 2025/2/14 
 */
public class FileClassifier {
    // 文件类型映射
    private static final Map<String, String> FILE_TYPE_MAP = new HashMap<>();

    static {
        // Word 文件类型
        FILE_TYPE_MAP.put("doc", "Word");
        FILE_TYPE_MAP.put("docx", "Word");

        // PPT 文件类型
        FILE_TYPE_MAP.put("ppt", "PPT");
        FILE_TYPE_MAP.put("pptx", "PPT");

        // PDF 文件类型
        FILE_TYPE_MAP.put("pdf", "PDF");

        // 影像文件类型
        FILE_TYPE_MAP.put("jpg", "影像");
        FILE_TYPE_MAP.put("jpeg", "影像");
        FILE_TYPE_MAP.put("png", "影像");
        FILE_TYPE_MAP.put("bmp", "影像");
        FILE_TYPE_MAP.put("gif", "影像");
        FILE_TYPE_MAP.put("tiff", "影像");
        FILE_TYPE_MAP.put("mp4", "影像");
    }

    /**
     * 根据文件后缀名分类文件
     *
     * @param filePath 文件路径
     * @return 文件类型，如果未知则返回 "未知"
     */
    public static String classifyFile(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();

        // 获取文件后缀名
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            String extension = fileName.substring(dotIndex + 1).toLowerCase();
            return FILE_TYPE_MAP.getOrDefault(extension, "未知");
        } else {
            return "未知";
        }
    }

    public static void main(String[] args) {
        // 测试分类方法
        String[] testFiles = {
                "example.doc",
                "presentation.pptx",
                "document.pdf",
                "photo.jpg",
                "unknown_file.txt"
        };

        for (String file : testFiles) {
            System.out.println("文件: " + file + " 类型: " + classifyFile(file));
        }
    }
}
