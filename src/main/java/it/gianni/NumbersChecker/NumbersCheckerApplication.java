package it.gianni.NumbersChecker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import it.gianni.NumbersChecker.storage.StorageProperties;
import it.gianni.NumbersChecker.storage.StorageService;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableConfigurationProperties(StorageProperties.class)
public class NumbersCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumbersCheckerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
