package cn.myforever.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "cn.myforever.es.dao")
public class ESApp {

	public static void main(String[] args) {
		SpringApplication.run(ESApp.class, args);
	}

}
