package com.marcobehler.myfancypdfinvoices.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//so that Spring’s @ComponentScan can find it and make Spring MVC understand: This class can accept HTTP requests
@Controller 
public class MyFirstSpringController {
    @GetMapping ("/")
    @ResponseBody 
    public String helloWorld() {
        return "hello World";
    }
}
