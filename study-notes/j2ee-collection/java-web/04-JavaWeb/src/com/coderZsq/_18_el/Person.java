package com.coderZsq._18_el;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@Setter
@Getter
public class Person {
    private String username = "Lucy";
    private Integer age = 18;
    private String[] hobbys = {"java", "c", "girl"};
    private List<String> list = Arrays.asList("list1", "list2", "list3");
    private Map<String, Object> map = new HashMap<String, Object>() {
        {
            this.put("company", "华润");
            this.put("englishName", "China Resources");
            this.put("coderZsq.github.io", "域名");
        }
    };
}
