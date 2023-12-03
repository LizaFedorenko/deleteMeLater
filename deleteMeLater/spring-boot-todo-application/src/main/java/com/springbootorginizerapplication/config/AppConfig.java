package com.springbootorginizerapplication.config;

import com.springbootorginizerapplication.repositories.FileTodoItemRepository;
import com.springbootorginizerapplication.repositories.TodoItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TodoItemRepository todoItemRepository1() {
        // Provide the file path where data will be stored
        return new FileTodoItemRepository ( "data/todoitems.dat" );
    }
}

