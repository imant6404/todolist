package com.coursework.todolist.service;

import com.coursework.todolist.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment getOne(int id);

    void save(Comment comment);

    void deleteById(int id);

    List<Comment> findCommentByTask(int id);

}
