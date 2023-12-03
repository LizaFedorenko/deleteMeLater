package com.springbootorginizerapplication;

import com.springbootorginizerapplication.repositories.FileTodoItemRepository;
import com.springbootorginizerapplication.models.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FileTodoItemRepositoryTest {

    private static final String TEST_FILE_PATH = "test_todo_items.dat";

    private FileTodoItemRepository repository;

    @BeforeEach
    void setUp() {
        // Clean up existing test file before each test
        new File(TEST_FILE_PATH).delete();

        // Initialize repository with test file path
        repository = new FileTodoItemRepository(TEST_FILE_PATH);
    }

    

    @Test
    void deleteById() {
        // Save the item
        repository.save(createTodoItem(1L, "Test Task", false));

        // Delete the item by ID
        repository.deleteById(1L);

        // Ensure the item is deleted
        Optional<TodoItem> deletedItem = repository.findById(1L);
        assertFalse(deletedItem.isPresent());
    }

    @Test
    void deleteAll() {
        List<TodoItem> todoItems = createSampleTodoItems();

        // Save all items
        repository.saveAll(todoItems);

        // Delete all items
        repository.deleteAll();

        // Ensure all items are deleted
        Iterable<TodoItem> remainingItems = repository.findAll();
        assertFalse(remainingItems.iterator().hasNext());
    }
    // Add more tests for other methods as needed

    private List<TodoItem> createSampleTodoItems() {
        List<TodoItem> todoItems = new ArrayList<>();
        todoItems.add(createTodoItem(1L, "Task 1", false));
        todoItems.add(createTodoItem(2L, "Task 2", true));
        todoItems.add(createTodoItem(3L, "Task 3", false));
        return todoItems;
    }

    private TodoItem createTodoItem(Long id, String description, boolean completed) {
        // Create TodoItem using an alternative method
        TodoItem todoItem = new TodoItem();
        todoItem.setId(id);
        todoItem.setDescription(description);
        return todoItem;
    }
}
