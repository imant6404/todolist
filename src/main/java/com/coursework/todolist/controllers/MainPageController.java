package com.coursework.todolist.controllers;

import com.coursework.todolist.entity.Author;
import com.coursework.todolist.entity.Task;
import com.coursework.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping({"/main-page"})
public class MainPageController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/list")
    public String taskList(Model model, HttpServletRequest request) {
        List<Task> taskList = null;
        Author author = (Author) request.getSession().getAttribute("author");
        if (author != null) {
            taskList = taskService.findTask(author.getId());
        } else {
            return "redirect:/author/login";
        }

        model.addAttribute("taskList", taskList);
        model.addAttribute("author", author);
        return "/main-page/list";
    }

    @RequestMapping("/member-list")
    public String memberList(Model model, HttpServletRequest request) {
        List<Task> memberTaskList = null;
        Author author = (Author) request.getSession().getAttribute("author");
        if (author != null) {
            memberTaskList = author.getMemberList();
        } else {
            return "redirect:/author/login";
        }
        model.addAttribute("memberTaskList", memberTaskList);
        model.addAttribute("author", author);
        return "/main-page/member-list";
    }

}
