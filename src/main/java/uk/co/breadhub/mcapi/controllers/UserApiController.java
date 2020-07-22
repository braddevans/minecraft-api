package uk.co.breadhub.mcapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.breadhub.mcapi.model.User;
import uk.co.breadhub.mcapi.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("")
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/new")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/api/all")
    public Iterable<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/api/{id}")
    public Optional<User> getById(@PathVariable(value = "id") Long id) {
        return userService.find(id);
    }

    @GetMapping("/api/{uuid}")
    public Optional<User> getByUUID(@PathVariable(value = "uuid") String uuid) {
        return userService.findByUUID(uuid);
    }

    @GetMapping("/api/count")
    public long count() {
        return userService.count();
    }




    /* ==========================
     * Removed Mappings
     * ==========================
     */

    /*
    @DeleteMapping("/api/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){
        userService.delete(id);
    }
    */
    /*
    @DeleteMapping("/api")
    public void deleteAll(){
        userService.deleteAll();
    }
    */
}