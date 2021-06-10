package com.coursework.todolist.service;

import com.coursework.todolist.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task getOne(int id);

    void save(Task task);

    void deleteById(int id);

    public List<Task> findTask(int id);

}
