package com.coursework.todolist.service;


import com.coursework.todolist.dao.AuthorDao;
import com.coursework.todolist.entity.Author;
import com.coursework.todolist.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorDao authorDao;

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    public Author getOne(int id) {
        return authorDao.getOne(id);
    }

    @Override
    public void save(Author author) {
        authorDao.save(author);
    }

    @Override
    public void deleteById(int id) {
        authorDao.deleteById(id);
    }

    @Override
    public Author findByEmail(String email) {
        List<Author> authors = authorDao.findAll();
        for(Author author : authors){
            if(author.getEmail().equals(email.trim())){
                return author;
            }
        }
        return null;
    }

}
