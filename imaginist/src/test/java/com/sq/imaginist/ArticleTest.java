package com.sq.imaginist;

import com.sq.imaginist.pojo.po.Article;
import com.sq.imaginist.service.ArticleService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ImaginistApplication.class)
public class ArticleTest {
    @Autowired
    private ArticleService service;

    @Test
    public void saveStockArticles() throws Exception {
        File directory = new File("/Users/zhushuangquan/Codes/GitHub/coderZsq.github.io/_posts");
        if (directory.isFile() || !directory.exists()) {
            return;
        }
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (!"md".equals(FilenameUtils.getExtension(file.getName()))) continue;
            String input = FileUtils.readFileToString(file, "UTF-8");
            Article article = new Article();
            article.setContent(input.split("\n---\n\n")[1]
                            .replaceAll("!\\[.*\\]\\(.*\\)", "")
                            .replaceAll("<img.*\n", "")
                            .replaceAll("\".*/>", "")
            );
            article.setTitle(matchColumn(input, "title"));
            article.setType("stock");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            article.setDate(fmt.parse(matchColumn(input, "date")));
            int words = wordCount(input);
            article.setWords(words);
            article.setDuration(words / 350);
            service.save(article);
        }
    }

    private String matchColumn(String input, String column) {
        Pattern pattern = Pattern.compile(column + ": (.+)\n");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) return null;
        return matcher.group().substring(column.length() + 2).trim();
    }

    private int wordCount(String string) {
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
