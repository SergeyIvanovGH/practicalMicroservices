package com.practicalmicroservices.userservice;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserserviceApplication {

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${Connect.database}")
	private String database;

	@Bean(initMethod = "migrate")
	public Flyway flyway() {
/**
 As flyway Db need url seperetaly that is why we extracting only url from given string
 */
//		String urlWithoutDatabaseName = url.substring(0, url.lastIndexOf("/"));
		Flyway flyway = new Flyway();
		flyway.setDataSource(url, userName, password);
		flyway.setSchemas(database);
		flyway.setBaselineOnMigrate(true);

		return flyway;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

}
