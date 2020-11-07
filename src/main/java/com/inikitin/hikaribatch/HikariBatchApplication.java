package com.inikitin.hikaribatch;

import com.inikitin.hikaribatch.repository.BatchRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(repositoryBaseClass = BatchRepositoryImpl.class)
@EnableTransactionManagement
@SpringBootApplication
public class HikariBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(HikariBatchApplication.class, args);
	}

}
