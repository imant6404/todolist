package com.coursework.todolist.service;


import com.coursework.todolist.dao.TaskDao;
import com.coursework.todolist.entity.Author;
import com.coursework.todolist.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Override
    public Task getOne(int id) {
        return taskDao.getOne(id);
    }

    @Override
    public void save(Task task) {
        taskDao.save(task);
    }

    @Override
    public void deleteById(int id) {
        taskDao.deleteById(id);
    }

    public List<Task> findTask(int id) {
        List<Task> list = taskDao.findAll();
        List<Task> newList = new ArrayList<>();
        for (Task task : list) {
            if (task.getAuthor().getId() == id)
                newList.add(task);
        }
        return newList;
    }
}
