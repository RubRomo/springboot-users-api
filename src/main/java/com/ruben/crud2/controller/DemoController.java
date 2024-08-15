package com.ruben.crud2.controller;


import com.ruben.crud2.model.User;
import com.ruben.crud2.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {

    @Autowired
    private IUserRepository repo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        System.out.println("test 2");
        System.out.println("new message");
        return "greeting";
    }

    @GetMapping("/new")
    public String newUser(){
        User u1 = new User();
        u1.setName("Ruben");
        u1.setLastname("Romo");
        u1.setEmail("correo@gmail.com");
        u1.setPassword("123");
        System.out.println(u1);
        repo.save(u1);
        return "index";
    }

}
