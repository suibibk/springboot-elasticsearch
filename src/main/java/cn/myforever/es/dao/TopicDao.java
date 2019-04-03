package cn.myforever.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import cn.myforever.es.entity.Topic;

public interface TopicDao extends ElasticsearchRepository<Topic, Long>{
	
}
