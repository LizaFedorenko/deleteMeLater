package com.springbootorginizerapplication;
import com.springbootorginizerapplication.controllers.TodoItemController;
import com.springbootorginizerapplication.models.TodoItem;
import com.springbootorginizerapplication.repositories.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ToDoItemControllerTests {
        @Mock
        private TodoItemRepository todoItemRepository;

        @InjectMocks
        private TodoItemController todoItemController;

        @Test
        public void testIndex() {
            // Mock data
            List < TodoItem > todoItems = Arrays.asList(
                    new TodoItem("Task 1"),
                    new TodoItem("Task 2")
            );
            when(todoItemRepository.findAll()).thenReturn(todoItems);

            // Invoke the method
            ModelAndView modelAndView = todoItemController.index();

            // Assertions
            assertEquals("tasks", modelAndView.getViewName());
            assertEquals(todoItems, modelAndView.getModel().get("todoItems"));
            assertNotNull(modelAndView.getModel().get("today"));
            verify(todoItemRepository, times(1)).findAll();
        }

        @Test
        public void testCreateTodoItemWithValidData() {
            // Mock data
            TodoItem todoItem = new TodoItem("New Task");
            BindingResult bindingResult = mock(BindingResult.class);
            when(bindingResult.hasErrors()).thenReturn(false);

            // Invoke the method
            String result = todoItemController.createTodoItem(todoItem, bindingResult, mock(Model.class));

            // Assertions
            assertEquals("redirect:/", result);
            assertNotNull(todoItem.getCreatedDate());
            assertNotNull(todoItem.getModifiedDate());
            verify(todoItemRepository, times(1)).save(any(TodoItem.class));
        }

        @Test
        public void testCreateTodoItemWithInvalidData() {
            // Mock data
            TodoItem todoItem = new TodoItem(); // Invalid data, should trigger validation error
            BindingResult bindingResult = mock(BindingResult.class);
            when(bindingResult.hasErrors()).thenReturn(true);

            // Invoke the method
            String result = todoItemController.createTodoItem(todoItem, bindingResult, mock(Model.class));

            // Assertions
            assertEquals("add-todo-item", result);
            verify(todoItemRepository, never()).save(any(TodoItem.class));
        }
    @Test
    public void testDefaultConstructor() {
        TodoItem todoItem = new TodoItem();
        assertNull(todoItem.getId());
        assertNull(todoItem.getDescription());
        assertFalse(todoItem.isComplete());
        assertNull(todoItem.getCreatedDate());
        assertNull(todoItem.getModifiedDate());
        assertNull(todoItem.getImagePath());
    }

    @Test
    public void testParameterizedConstructor() {
        String description = "Sample Description";
        TodoItem todoItem = new TodoItem(description);

        assertNull(todoItem.getId());
        assertEquals(description, todoItem.getDescription());
        assertFalse(todoItem.isComplete());
        assertNotNull(todoItem.getCreatedDate());
        assertNotNull(todoItem.getModifiedDate());
        assertNull(todoItem.getImagePath());
    }

    @Test
    public void testSetters() {
        TodoItem todoItem = new TodoItem();

        // Set id
        Long id = 1L;
        todoItem.setId(id);
        assertEquals(id, todoItem.getId());

        // Set description
        String description = "Updated Description";
        todoItem.setDescription(description);
        assertEquals(description, todoItem.getDescription());

        // Set complete
        boolean complete = true;
        todoItem.setComplete(complete);
        assertTrue(todoItem.isComplete());

        // Set created date
        Instant createdDate = Instant.parse("2023-01-01T00:00:00Z");
        todoItem.setCreatedDate(createdDate);
        assertEquals(createdDate, todoItem.getCreatedDate());

        // Set modified date
        Instant modifiedDate = Instant.parse("2023-01-02T12:34:56Z");
        todoItem.setModifiedDate(modifiedDate);
        assertEquals(modifiedDate, todoItem.getModifiedDate());

        // Set image path
        String imagePath = "/new/path/to/image.jpg";
        todoItem.setImagePath(imagePath);
        assertEquals(imagePath, todoItem.getImagePath());
    }


        /*@Test
        public void testUpdateTodoItemWithValidData() {
            // Mock data
            long itemId = 1L;
            TodoItem existingTodoItem = new TodoItem("Task");
            when(todoItemRepository.findById(itemId)).thenReturn( Optional.of(existingTodoItem));
            TodoItem updatedTodoItem = new TodoItem("Updated Task");
            updatedTodoItem.setId(itemId);
            BindingResult bindingResult = mock(BindingResult.class);
            when(bindingResult.hasErrors()).thenReturn(false);

            // Invoke the method
            String result = todoItemController.updateTodoItem(itemId, updatedTodoItem, bindingResult, mock( Model.class));

            // Assertions
            assertEquals("redirect:/", result);
            assertNotNull(updatedTodoItem.getModifiedDate());
            verify(todoItemRepository, times(1)).save(updatedTodoItem);
        }

        @Test
        public void testUpdateTodoItemWithInvalidData() {
            // Mock data
            long itemId = 1L;
            TodoItem existingTodoItem = new TodoItem("Task");
            when(todoItemRepository.findById(itemId)).thenReturn(Optional.of(existingTodoItem));
            TodoItem updatedTodoItem = new TodoItem(); // Invalid data, should trigger validation error
            updatedTodoItem.setId(itemId);
            BindingResult bindingResult = mock(BindingResult.class);
            when(bindingResult.hasErrors()).thenReturn(true);

            // Invoke the method
            String result = todoItemController.updateTodoItem(itemId, updatedTodoItem, bindingResult, mock(Model.class));

            // Assertions
            assertEquals("update-todo-item", result);
            verify(todoItemRepository, never()).save(updatedTodoItem);
        }*/
    }
