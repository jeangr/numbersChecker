package it.gianni.numberschecker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import it.gianni.numberschecker.properties.StorageProperties;
import it.gianni.numberschecker.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class NumbersCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumbersCheckerApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteDirectory();
            storageService.init();
        };
    }

}
