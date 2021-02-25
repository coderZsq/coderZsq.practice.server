package com.sq.esdemo.dao;

import com.sq.esdemo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    @Query(" \"match\": {\"title\": \"?0\"} ")
    public Page<Product> queryByTitle(String title, Pageable pageable);
}
