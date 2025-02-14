package vip.xiaonuo.biz.modular.nlp;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.*;

import java.util.*;
import java.util.stream.*;

/**
 * 1. @description:
 * 2. @author: zzb
 * 3. @time: 2025/2/10
 */
public class NLPExample {
    public static void main(String[] args) {
        // 设置Stanford NLP的属性
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");

        // 加载Stanford NLP的管道
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // 输入的文章
        String text = "On October 15, 2023, the annual conference took place at Stanford University.";

        // 创建空的Annotation对象
        Annotation document = new Annotation(text);

        // 运行所有的Annotator在文本上
        pipeline.annotate(document);

        // 获取处理后的句子
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        Map<String, String> eventInfo = new HashMap<>();
        String eventTitle = "";
        String eventTime = "";
        String eventLocation = "";

        for (CoreMap sentence : sentences) {
            // 获取命名实体（NER）
            List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);
            for (CoreLabel token : tokens) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                if (ner.equals("O")) continue; // 忽略非命名实体

                // 简单规则来提取信息（可以根据需要改进）
                if (ner.startsWith("ORGANIZATION") || ner.startsWith("EVENT")) {
                    eventTitle = word; // 这里假设事件标题是首个识别的组织或事件名
                } else if (ner.equals("DATE")) {
                    eventTime = word;
                } else if (ner.equals("LOCATION")) {
                    eventLocation = word;
                }
            }
        }

        // 打印结果
        eventInfo.put("Event Title", eventTitle);
        eventInfo.put("Event Time", eventTime);
        eventInfo.put("Event Location", eventLocation);

        eventInfo.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
