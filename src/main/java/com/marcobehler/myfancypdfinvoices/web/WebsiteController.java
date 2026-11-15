package com.marcobehler.myfancypdfinvoices.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // not RestController, because we want to return HTML from our methods, not JSON or XML or plain text.
public class WebsiteController {
    @GetMapping ("/")
    public String homePage() {
        return "index.html";
    }
}
