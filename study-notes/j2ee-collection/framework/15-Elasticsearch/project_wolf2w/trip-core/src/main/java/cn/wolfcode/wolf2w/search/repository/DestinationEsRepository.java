package cn.wolfcode.wolf2w.search.repository;

import cn.wolfcode.wolf2w.search.domain.DestinationEs;
import cn.wolfcode.wolf2w.search.domain.TravelEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DestinationEsRepository extends ElasticsearchRepository<DestinationEs, String>{
}
