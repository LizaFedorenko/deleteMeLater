package com.wazooinc.springboottodoapplication.repositories;

import com.wazooinc.springboottodoapplication.models.TodoItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {

}
