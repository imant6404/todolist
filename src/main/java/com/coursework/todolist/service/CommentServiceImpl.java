package com.coursework.todolist.service;

import com.coursework.todolist.dao.CommentDao;
import com.coursework.todolist.dao.TaskDao;
import com.coursework.todolist.entity.Comment;
import com.coursework.todolist.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TaskDao taskDao;

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public Comment getOne(int id) {
        return commentDao.getOne(id);
    }

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void deleteById(int id) {
        commentDao.deleteById(id);
    }

    @Override
    public List<Comment> findCommentByTask(int id) {
        Task task = taskDao.getOne(id);
        List<Comment> comments = task.getComments();
        return comments;
    }
}
