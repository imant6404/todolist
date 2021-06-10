package com.coursework.todolist.controllers;

import com.coursework.todolist.entity.Author;
import com.coursework.todolist.entity.Comment;
import com.coursework.todolist.entity.Task;
import com.coursework.todolist.service.AuthorService;
import com.coursework.todolist.service.CommentService;
import com.coursework.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskPageController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/task-page")
    public String taskWork(Model model, @RequestParam("taskId") int id, HttpServletRequest request) {
        Author author = (Author) request.getSession().getAttribute("author");
        Task task = taskService.getOne(id);

        List<Comment> commentList = commentService.findCommentByTask(id);
        List<Author> memberList = null;
        List<Author> newMemberList = new ArrayList<>();
        memberList = task.getMemberTaskList();

        for(Author author1 : memberList){
            if(!newMemberList.contains(author1))
            newMemberList.add(author1);
        }

        Comment newComment = new Comment();
        newComment.setTask(task);
        newComment.setCommentAuthor(author);
        model.addAttribute("author", author);
        model.addAttribute("task", task);
        model.addAttribute("comments", commentList);
        model.addAttribute("members", newMemberList);
        model.addAttribute("newComment", newComment);
        return "/task/task-page";
    }

    @RequestMapping("/save-comment")
    public String save(@ModelAttribute("comment") Comment comment, @RequestParam("taskId") int id,
                       HttpServletRequest request) {
        Author author = (Author) request.getSession().getAttribute("author");
        Task task = taskService.getOne(id);
        comment.setTask(task);
        comment.setCommentAuthor(author);
        commentService.save(comment);
        return "redirect:/task/task-page?taskId=" + task.getId();
    }

    @RequestMapping("/send-invite")
    private String sendInvite(Model model, @RequestParam("taskId") int id) {
        Author author = new Author();
        Task task = taskService.getOne(id);
        model.addAttribute("author", author);
        model.addAttribute("task", task);
        return "/task/send-invite";
    }

    @RequestMapping("/process-invite")
    private String processInvite(@ModelAttribute("author") Author author, @RequestParam("taskId") int id,
                                 HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Author foundAuthor = authorService.findByEmail(author.getEmail().trim());
        if (taskService.getOne(id).getMemberTaskList().contains(foundAuthor)) {
            redirectAttributes.addFlashAttribute("error", "Данного пользователь уже есть в списке");
            return "redirect:/task/send-invite?taskId=" + id;
        } else {
            if (foundAuthor != null) {
                Task task = taskService.getOne(id);
                task.addMemberInTask(foundAuthor);
                taskService.save(task);
                return "redirect:/task/task-page?taskId=" + id;
            }
        }
        redirectAttributes.addFlashAttribute("error", "Данного пользователя не существует");
        return "redirect:/task/send-invite?taskId=" + id;
    }
}
