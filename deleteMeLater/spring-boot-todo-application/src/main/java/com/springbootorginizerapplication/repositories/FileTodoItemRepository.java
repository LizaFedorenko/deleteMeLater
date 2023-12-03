package com.springbootorginizerapplication.repositories;

import com.springbootorginizerapplication.models.TodoItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileTodoItemRepository implements TodoItemRepository {

    private final String filePath;

    public FileTodoItemRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public <S extends TodoItem> S save(S entity) {
        List<TodoItem> todoItems = getAllItems();
        todoItems.add(entity);
        saveAllItems(todoItems);
        return entity;
    }

    @Override
    public <S extends TodoItem> Iterable<S> saveAll(Iterable<S> entities) {
        List<TodoItem> todoItems = getAllItems();
        entities.forEach(todoItems::add);
        saveAllItems(todoItems);
        return entities;
    }

    @Override
    public Optional < TodoItem > findById ( Long aLong ) {
        return Optional.empty ( );
    }

    @Override
    public boolean existsById ( Long aLong ) {
        return false;
    }

    @Override
    public Iterable<TodoItem> findAll() {
        return getAllItems();
    }

    @Override
    public Iterable<TodoItem> findAllById(Iterable<Long> ids) {
        List<TodoItem> todoItems = getAllItems();
        List<TodoItem> result = new ArrayList<>();
        ids.forEach(id -> todoItems.stream().filter(item -> item.getId().equals(id)).findFirst().ifPresent(result::add));
        return result;
    }

    @Override
    public long count() {
        return getAllItems().size();
    }

    @Override
    public void deleteById(Long id) {
        List<TodoItem> todoItems = getAllItems();
        todoItems.removeIf(item -> item.getId().equals(id));
        saveAllItems(todoItems);
    }

    @Override
    public void delete(TodoItem entity) {
        List<TodoItem> todoItems = getAllItems();
        todoItems.remove(entity);
        saveAllItems(todoItems);
    }

    @Override
    public void deleteAllById ( Iterable < ? extends Long > longs ) {

    }

    @Override
    public void deleteAll(Iterable<? extends TodoItem> entities) {
        List<TodoItem> todoItems = getAllItems();
        entities.forEach(todoItems::remove);
        saveAllItems(todoItems);
    }

    @Override
    public void deleteAll() {
        saveAllItems(new ArrayList<>());
    }

    private List<TodoItem> getAllItems() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<TodoItem>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveAllItems(List<TodoItem> todoItems) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(todoItems);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly in a real application
        }
    }
}