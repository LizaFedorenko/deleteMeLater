package com.springbootorginizerapplication.repositories;

import com.springbootorginizerapplication.models.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository < TodoItem, Long> {

}
