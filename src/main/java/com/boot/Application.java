package com.boot;

import com.boot.entity.Unit;
import com.boot.repository.UnitRepository;
import com.boot.service.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.stream.Stream;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Resource
    FilesStorageService storageService;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
//        storageService.init();
    }
/*    @Bean
    CommandLineRunner init(UnitRepository unitRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                Unit unit = new Unit(name, name.toLowerCase() + "@domain.com");
                userRepository.save(user);
            });
            userRepository.findAll().forEach(System.out::println);
        };
    }*/
}
