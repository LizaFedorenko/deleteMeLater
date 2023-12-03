package com.springbootorginizerapplication;

import com.springbootorginizerapplication.controllers.TodoFormController;
import com.springbootorginizerapplication.models.TodoItem;
import com.springbootorginizerapplication.repositories.TodoItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ToDoFormControllerTest {

    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoFormController todoFormController;

    @Test
    public void showCreateForm_ShouldReturnAddTodoItemView() {
        // Act
        String viewName = todoFormController.showCreateForm(new TodoItem ());

        // Assert
        assertEquals("add-todo-item", viewName);
    }

    @Test
    public void showUpdateForm_ShouldReturnUpdateTodoItemView_WhenTodoItemExists() {
        // Arrange
        long todoId = 1L;
        TodoItem mockTodoItem = new TodoItem();
        mockTodoItem.setId(todoId);
        when(todoItemRepository.findById(todoId)).thenReturn(Optional.of(mockTodoItem));
        Model model = new ExtendedModelMap(); // Spring's Model interface

        // Act
        String viewName = todoFormController.showUpdateForm(todoId, model);

        // Assert
        assertEquals("update-todo-item", viewName);
        assertTrue(model.containsAttribute("todo"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void showUpdateForm_ShouldThrowIllegalArgumentException_WhenTodoItemDoesNotExist() {
        // Arrange
        long nonExistentTodoId = 99L;
        when(todoItemRepository.findById(nonExistentTodoId)).thenReturn(Optional.empty());
        Model model = new ExtendedModelMap(); // Spring's Model interface

        // Act
        todoFormController.showUpdateForm(nonExistentTodoId, model);
    }

    @Test
    public void deleteTodoItem_ShouldDeleteTodoItemAndRedirectToRoot() {
        // Arrange
        long todoId = 1L;
        TodoItem mockTodoItem = new TodoItem();
        mockTodoItem.setId(todoId);
        when(todoItemRepository.findById(todoId)).thenReturn(Optional.of(mockTodoItem));

        // Act
        String redirect = todoFormController.deleteTodoItem(todoId, new ExtendedModelMap());

        // Assert
        verify(todoItemRepository).delete(mockTodoItem);
        assertEquals("redirect:/", redirect);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTodoItem_ShouldThrowIllegalArgumentException_WhenTodoItemDoesNotExist() {
        // Arrange
        long nonExistentTodoId = 99L;
        when(todoItemRepository.findById(nonExistentTodoId)).thenReturn( Optional.empty());

        // Act
        todoFormController.deleteTodoItem(nonExistentTodoId, new ExtendedModelMap ());
    }
}
