package com.coursework.todolist.dao;


import com.coursework.todolist.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Integer> {

}


