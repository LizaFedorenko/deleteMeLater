package com.wazooinc.springboottodoapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.wazooinc.springboottodoapplication.controllers.TodoFormController;
import com.wazooinc.springboottodoapplication.models.TodoItem;
import com.wazooinc.springboottodoapplication.repositories.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@SpringBootTest
class SpringBootTodoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testTodoItemToString() {
		TodoItem todoItem = new TodoItem();
		todoItem.setId(1L);
		todoItem.setDescription("Test Description");
		todoItem.setComplete(false);
		todoItem.setCreatedDate(Instant.parse("2023-01-01T12:00:00Z"));
		todoItem.setModifiedDate(Instant.parse("2023-01-01T13:00:00Z"));

		String expectedToString = "TodoItem{id=1, description='Test Description', complete='false', " +
				"createdDate='2023-01-01T12:00:00Z', modifiedDate='2023-01-01T13:00:00Z'}";

		assertEquals(expectedToString, todoItem.toString());
	}
}
