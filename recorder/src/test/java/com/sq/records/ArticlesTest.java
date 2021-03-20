package com.sq.records;

import com.sq.recorder.pojo.po.Article;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticlesTest {
    @Test
    public  void test(String[] args) throws IOException {
        load("/Users/zhushuangquan/Codes/GitHub/coderZsq.github.io/_posts");
    }

    public void load(String directoryPath) throws IOException {
        File directory = new File(directoryPath);
        if (directory.isFile() || !directory.exists()) {
            return;
        }
        List<Article> list = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (!"md".equals(FilenameUtils.getExtension(file.getName()))) continue;
            String input = FileUtils.readFileToString(file, "UTF8");
            Article article = new Article();
            article.setContent(input.split("---")[2]);
            article.setTitle(matchColumn(input, "title"));
            article.setDate(matchColumn(input, "date"));
            int words = wordCount(input);
            article.setWords(words);
            article.setDuration(words / 350);
            System.out.println(article.getDuration());
            list.add(article);
        }
    }

    public String matchColumn(String input, String column) {
        Pattern pattern = Pattern.compile(column + ": (.+)\n");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find())  return null;
        return matcher.group().substring(column.length() + 2).trim();
    }

    public static int wordCount(String string) {
        if (string == null) {
            return 0;
        }
        String englishString = string.replaceAll("[\u4e00-\u9fa5]", "");
        String[] englishWords = englishString.split("[\\p{P}\\p{S}\\p{Z}\\s]+");
        int chineseWordCount = string.length() - englishString.length();
        int otherWordCount = englishWords.length;
        if (englishWords.length > 0 && englishWords[0].length() < 1) {
            otherWordCount--;
        }
        if (englishWords.length > 1 && englishWords[englishWords.length - 1].length() < 1) {
            otherWordCount--;
        }
        return chineseWordCount + otherWordCount;
    }
}
