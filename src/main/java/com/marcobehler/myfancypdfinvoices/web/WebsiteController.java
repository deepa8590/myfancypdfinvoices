package com.marcobehler.myfancypdfinvoices.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marcobehler.myfancypdfinvoices.web.Forms.LoginForm;

@Controller  // not RestController, because we want to return HTML from our methods, not JSON or XML or plain text.
public class WebsiteController {
    @GetMapping ("/")
    public String homePage(Model model, @RequestParam (name = "username", required = false, defaultValue = "stranger") String username) {
        model.addAttribute("username", username);
        model.addAttribute("currentDateTime", LocalDateTime.now());
        return "index.html";
    }

    @GetMapping ("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login.html";
    }

    @PostMapping ("/login")
    //@ModelAttribute is equivalent to  model.put("loginForm", loginForm).
    public String login(@ModelAttribute LoginForm loginForm,Model model) {
        if (loginForm.getUsername().equals(loginForm.getPassword())) {
            return "redirect:/";
        }
        model.addAttribute("invalidCredentials", true);
        return "login.html";
    }
}
