package com.coursework.todolist.dao;

import com.coursework.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskDao extends JpaRepository<Task, Integer> {

}
