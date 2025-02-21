package vip.xiaonuo.biz.modular.paper.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFSearch {
    // 搜索PDF文件中的文字
    public static List<String> searchTextInPDF(String filePath, String searchText) throws IOException {
        List<String> results = new ArrayList<>();
        File file = new File(filePath);
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            if (text.contains(searchText)) {
                results.add(filePath);
            }
        }
        return results;
    }

    // 按PDF名称搜索
    public static List<String> searchPDFByName(String directoryPath, String fileName) {
        List<String> results = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.contains(fileName));
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".pdf")) {
                    results.add(file.getAbsolutePath());
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        String directoryPath = "E:\\白云白云湖大朗中路跨线桥“6·12”一车辆伤害事故调查报告.pdf";
//        String directoryPath = "path/to/pdf/directory";
        String searchText = "11111111111";
        String fileName = "白云白云湖大朗中";

        try {
            // 按名称搜索PDF
//            List<String> nameResults = searchPDFByName(directoryPath, fileName);
            List<String> nameResults = new ArrayList<>();
            File directory = new File(directoryPath);
            String directoryName = directory.getName();
            System.out.println("directoryName = " + directoryName);
            File[] files = directory.listFiles((dir, name) -> name.contains(fileName));

//            if (files != null) {
//                for (File file : files) {
                    if (directory.getName().endsWith(".pdf")) {
                        if (directoryName.contains(fileName)) {
                            System.out.println("directoryName = " + directoryName);
                            nameResults.add(directory.getAbsolutePath());
                        }
                    }
//                }
//            }
            System.out.println("按名称搜索结果: " + nameResults);

            // 搜索PDF中的文字
            List<String> textResults = new ArrayList<>();
            for (String filePath : nameResults) {
                textResults.addAll(searchTextInPDF(filePath, searchText));
            }
            System.out.println("按文字搜索结果: " + textResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
