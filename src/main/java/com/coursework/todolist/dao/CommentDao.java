package com.coursework.todolist.dao;

import com.coursework.todolist.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Integer> {

}
