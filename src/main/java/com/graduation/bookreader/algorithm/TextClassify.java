package com.graduation.bookreader.algorithm;

import org.springframework.util.ResourceUtils;
import org.thunlp.text.classifiers.BasicTextClassifier;
import org.thunlp.text.classifiers.ClassifyResult;
import org.thunlp.text.classifiers.LinearBigramChineseTextClassifier;

import java.io.FileNotFoundException;

/**
 * Description:
 * 弹幕分类器
 * <p>
 * Author: 丰杰
 * Date: 2020-10-27
 * Time: 22:21
 */
public class TextClassify {

    private static BasicTextClassifier classifier;

    static {
        classifier = new BasicTextClassifier();
        init();
    }

    private static void init() {
        try {
            classifier.loadCategoryListFromFile(ResourceUtils.getFile("classpath:my_novel_model/category").getAbsolutePath());
            classifier.setTextClassifier(new LinearBigramChineseTextClassifier(classifier.getCategorySize()));
            classifier.getTextClassifier().loadModel(ResourceUtils.getFile("classpath:my_novel_model").getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取分类级别结果
     * @param barrage 弹幕
     * @return 分类级别
     */
    public static Integer classify(String barrage) {
        ClassifyResult[] classifyResults = classifier.classifyText(barrage, 1);
        return Integer.parseInt(classifier.getCategoryName(classifyResults[0].label).split("-")[0]);
    }

}
