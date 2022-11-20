package com.example.demo.web;

import com.example.demo.database.UseForUser;
import com.example.demo.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebContent {
    private long id = 0;

    @Autowired
    UseForUser users;

    @GetMapping("/create/{name}")
    public String indexMain(@PathVariable String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        users.save(user);
        id++;
        return "create and add user with name - " + name;
    }

    @GetMapping("/show")
    public String indexForAll() {
        return "\r\n"
                + "   ALL USERS   - "
                + "\r\n"
                + users.findAll();
    }

    @RequestMapping("*")
    public String indexAll() {
        return "WORKING!!!";
    }
}
