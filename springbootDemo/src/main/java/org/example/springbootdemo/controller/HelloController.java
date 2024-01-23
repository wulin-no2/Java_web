package org.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// request handle class
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello from first springboot web app");
        return "hello from first springboot web app";
    }
}
