package com.example.demo.web;

import com.example.demo.database.UseForUser;
import com.example.demo.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;


@RestController
public class WebContent {
    private long id = 0;

    @Autowired
    UseForUser users;

    @GetMapping("/create/{name}")
    public String indexAdd(@PathVariable String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        users.save(user);
        id++;
        return "Create and add user with name - " + name;
    }

    /*  DON'T WORK  */
    @GetMapping("/deleteid/{id}")
    public String indexDeleteById(@PathVariable Long aId) {
        for (User user : users.findAll()) {
            if (aId.equals(user.getId())) {
                String timeName = user.getName();
                long timeId = user.getId();
                users.delete(user);
                replaceId();
                return "Delete user with name - " + timeName + " and id " + timeId;
            }
        }
        return "You made a mistake with the id ";
    }

    @GetMapping("/deletename/{name}")
    public String indexDeleteByName(@PathVariable String name) {
        for (User user : users.findAll()) {
            if (name.equals(user.getName())) {
                String timeName = user.getName();
                long timeId = user.getId();
                users.delete(user);
                replaceId();
                return "Delete user with name - " + timeName + " and id " + timeId;
            }
        }
        return "You made a mistake with the name ";
    }

    private void replaceId() {
        id = 1;
        for (User user : users.findAll()) {
            user.setId(id);
            id++;
        }
    }

    @GetMapping("/show")
    public String indexForAll() {
        return "\n\n"
                + "   ALL USERS   - "
                + "\n\n"
                + users.findAll();
    }

    @RequestMapping("*")
    public String indexAll() {
        return "WORKING!!!";
    }
}
