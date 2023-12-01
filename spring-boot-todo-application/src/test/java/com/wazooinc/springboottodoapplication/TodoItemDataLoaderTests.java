package com.wazooinc.springboottodoapplication;

import com.wazooinc.springboottodoapplication.config.TodoItemDataLoader;
import com.wazooinc.springboottodoapplication.models.TodoItem;
import com.wazooinc.springboottodoapplication.repositories.TodoItemRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.CommandLineRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoItemDataLoaderTests {

    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoItemDataLoader todoItemDataLoader;

    @Test
    public void testRun() {
        // Mock data
        TodoItem todoItem1 = new TodoItem("Example1");
        TodoItem todoItem2 = new TodoItem("Example2");

        when(todoItemRepository.count()).thenReturn(0L);
        when(todoItemRepository.save(todoItem1)).thenReturn(todoItem1);
        when(todoItemRepository.save(todoItem2)).thenReturn(todoItem2);

        // Invoke the run method
        try {
            todoItemDataLoader.run();
        } catch (Exception e) {
            // Handle exception if necessary
            e.printStackTrace();
        }

        // Verify that the repository save method is called twice
        verify(todoItemRepository).save(todoItem1);
        verify(todoItemRepository).save(todoItem2);
    }
}
