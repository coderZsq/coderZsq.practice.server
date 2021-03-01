package cn.wolfcode.wolf2w.search.repository;

import cn.wolfcode.wolf2w.search.domain.UserInfoEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserInfoEsRepository extends ElasticsearchRepository<UserInfoEs, String>{
}
