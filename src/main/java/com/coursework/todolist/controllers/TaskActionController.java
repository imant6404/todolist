package com.coursework.todolist.controllers;

import com.coursework.todolist.entity.Author;
import com.coursework.todolist.entity.Task;
import com.coursework.todolist.service.AuthorService;
import com.coursework.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskActionController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/task-form")
    public String form(Model model, HttpServletRequest request) {
        Task task = new Task();
        Author author = (Author) request.getSession().getAttribute("author");
        model.addAttribute("author", author);
        model.addAttribute("task", task);
        if (author != null) {
            return "/task/task-form";
        } else {
            return "redirect:/author/login";
        }
    }

    @RequestMapping("/save-task")
    public String save(@ModelAttribute("task") Task task, @RequestParam("authorId") int id, HttpServletRequest request) {
        if (task.getAuthor() == null)
            task.setAuthor(authorService.getOne(id));
        taskService.save(task);
        System.out.println("SAVED: " + task);
        return "redirect:/main-page/list";
    }

    @RequestMapping("/update")
    public String update(Model model, @RequestParam("taskId") int id, HttpServletRequest request) {
        Author author = (Author) request.getSession().getAttribute("author");
        model.addAttribute("author", author);
        Task task = taskService.getOne(id);
        model.addAttribute("task", task);
        return "task/task-form";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("taskId") int id) {
        taskService.deleteById(id);
        return "redirect:/main-page/list";
    }

}
