package com.coursework.todolist.service;

import com.coursework.todolist.entity.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author getOne(int id);

    void save(Author author);

    void deleteById(int id);

    public Author findByEmail(String email);

}
