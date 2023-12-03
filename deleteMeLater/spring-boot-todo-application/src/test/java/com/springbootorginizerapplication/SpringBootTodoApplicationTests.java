package com.springbootorginizerapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.springbootorginizerapplication.models.TodoItem;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
