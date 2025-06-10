package com.tkachenko.yevhen.workout.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tkachenko.yevhen.workout.entity.Exercise;
import com.tkachenko.yevhen.workout.entity.User;
import com.tkachenko.yevhen.workout.repository.ExerciseRepository;
import com.tkachenko.yevhen.workout.repository.UserRepository;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ExerciseRepository exerciseRepo, UserRepository userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (exerciseRepo.count() == 0) {
                Exercise pullUp = new Exercise();
                pullUp.setName("Pull-up");
                pullUp.setDescription("An upper body strength exercise");
                exerciseRepo.save(pullUp);
            }

            if (userRepo.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@admin.com");
                admin.setPasswordHash(passwordEncoder.encode("admin"));
                admin.setHeight(169f);
                admin.setCreatedAt(LocalDateTime.now());
                userRepo.save(admin);
            }
        };
    }
}
