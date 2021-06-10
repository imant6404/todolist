package com.coursework.todolist.controllers;


import com.coursework.todolist.entity.Author;
import com.coursework.todolist.service.AuthorService;
import com.coursework.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/author", ""})
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping({"/login", ""})
    public String login(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "/author/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("author");
        return "redirect:/author/login";
    }

    @RequestMapping("/validation")
    public String validation(@ModelAttribute("author") Author author, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        Author foundAuthor = authorService.findByEmail(author.getEmail().trim());
        if (foundAuthor != null) {
            if (foundAuthor.getPassword().trim().equals(author.getPassword().trim())) {
                request.getSession().setAttribute("author", foundAuthor);
                return "redirect:/main-page/list";
            }
        }
        redirectAttributes.addFlashAttribute("errorMessage", "email or password incorrect, try again");
        return "redirect:/author/login";
    }

}
