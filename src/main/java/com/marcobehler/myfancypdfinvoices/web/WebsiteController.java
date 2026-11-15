package com.marcobehler.myfancypdfinvoices.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  // not RestController, because we want to return HTML from our methods, not JSON or XML or plain text.
public class WebsiteController {
    @GetMapping ("/")
    public String homePage(Model model, @RequestParam (name = "username", required = false, defaultValue = "stranger") String username) {
        model.addAttribute("username", username);
        model.addAttribute("currentDateTime", LocalDateTime.now());
        return "index.html";
    }
}
