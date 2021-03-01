package cn.wolfcode.wolf2w.search.service;

import cn.wolfcode.wolf2w.search.query.SearchQueryObject;
import org.springframework.data.domain.Page;

public interface ISearchService {

    /**
     * 全文搜索 + 高亮显示
     * @param index 索引
     * @param type  类型
     * @param clz
     * @param qo
     * @param fields
     * @param <T>
     * @return 带有分页的全文搜索(高亮显示)结果集
     */
    <T> Page<T> searchWithHighlight(String index, String type, Class<T> clz, SearchQueryObject qo, String... fields);
}
