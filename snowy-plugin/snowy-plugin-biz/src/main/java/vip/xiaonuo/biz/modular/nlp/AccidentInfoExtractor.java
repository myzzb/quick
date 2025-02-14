package vip.xiaonuo.biz.modular.nlp;


import java.util.*;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.*;
/**
 1. @description:  
 2. @author: zzb
 3. @time: 2025/2/8 
 */
public class AccidentInfoExtractor {
    public static void main(String[] args) {
        // 设置Stanford NLP属性
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");

        // 创建StanfordCoreNLP对象
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // 输入文本
        String text = "LOCATION：2025年2月10日 地点：西安是雁塔区丈八一路发生一起重大交通事故";

        // 创建Annotation对象
        Annotation document = new Annotation(text);

        // 运行NLP管道
        pipeline.annotate(document);

        // 存储提取的信息
        Map<String, Set<String>> extractedInfo = new HashMap<>();
        extractedInfo.put("事故类型", new HashSet<>());
        extractedInfo.put("地点", new HashSet<>());
        extractedInfo.put("时间", new HashSet<>());

        // 遍历句子和词汇
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // 获取NER标注
                String nerLabel = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                // 根据NER标注提取信息
                if (nerLabel != null && !nerLabel.isEmpty()) {
                    String word = token.get(CoreAnnotations.TextAnnotation.class);
                    switch (nerLabel) {
                        case "ORGANIZATION": // 假设事故类型可能被误标为组织名（需要根据实际情况调整）
                        case "MISC": // 其他类型，可能包含事故类型
                            extractedInfo.get("事故类型").add(word);
                            break;
                        case "LOCATION":
                            extractedInfo.get("地点").add(word);
                            break;
                        case "DATE":
                        case "TIME":
                            extractedInfo.get("时间").add(word);
                            break;
                        // 可以添加更多case来处理其他类型的实体
                    }
                }
            }
        }

        // 输出提取的信息
        for (Map.Entry<String, Set<String>> entry : extractedInfo.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
