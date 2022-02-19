package com.bookwyrm.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping
    public String indexPage(){
        return "try adding /helloWorld";
    }

    @RequestMapping("/helloWorld")
    public String helloWorld(){
        return "Hello World!";
    }
}
