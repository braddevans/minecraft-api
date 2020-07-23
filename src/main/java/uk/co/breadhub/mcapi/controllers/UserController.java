package uk.co.breadhub.mcapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.breadhub.mcapi.model.User;
import uk.co.breadhub.mcapi.service.UserService;
import uk.co.breadhub.mcapi.utils.Utils;

import java.util.Optional;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private Utils utils;

    @Autowired
    private UserService userService;

    @PostMapping("/api/new")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/api/uuid/{id}")
    public User getById(@PathVariable(value = "id") String id) {
        return userService.findByUuid(id).get();
    }

    @GetMapping("/api/name/{name}")
    public User getByName(@PathVariable(value = "name") String name) {
        User user = null;
        if (!userService.findByName(name).isEmpty()){
            user = userService.findByName(name).get();
        } else {
            user = utils.newUser(name);
        }
        return user;
    }

    @GetMapping("/api/all")
    public Iterable<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/user/count")
    public long count() {
        return userService.count();
    }


    // ==================
    // Removed Mappings
    // ==================

    /*
    @DeleteMapping("/api/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){
        userService.delete(id);
    }

    @DeleteMapping("/user")
    public void deleteAll(){
        userService.deleteAll();
    }
    */
}