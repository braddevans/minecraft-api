package uk.co.breadhub.mcapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.co.breadhub.mcapi.model.PrevNames;
import uk.co.breadhub.mcapi.model.User;
import uk.co.breadhub.mcapi.service.PrevNamesService;
import uk.co.breadhub.mcapi.service.UserService;
import uk.co.breadhub.mcapi.utils.Utils;

import java.util.List;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private Utils utils;

    @Autowired
    private UserService userService;

    @Autowired
    private PrevNamesService prevnamesService;

    @PostMapping("/api/new")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/api/uuid/{id}")
    public User getByUuid(@PathVariable(value = "uuid") String uuid) {
        return userService.findByUuid(uuid).get();
    }

    @GetMapping("/api/name/{name}")
    public User getByName(@PathVariable(value = "name") String name) {
        User user = null;
        if (!userService.findByName(name).isEmpty()) {
            user = userService.findByName(name).get();
        } else {
            user = utils.newUser(name);
        }
        return user;
    }

    @GetMapping("/api/update/{uuid}/")
    public String updatePrevByUuid(@PathVariable(value = "uuid") String uuid) {
        String names = utils.updatePrevNames(uuid);
        User user = getByUuid(uuid);
        return "OK";
    }

    @GetMapping("/api/name/{name}/names")
    public List<PrevNames> getPrevByName(@PathVariable(value = "name") String name) {
        User user = getByName(name);
        return prevnamesService.findByUuid(user.getUuid());
    }

    @GetMapping("/api/uuid/{uuid}/names")
    public List<PrevNames> getPrevByUuid(@PathVariable(value = "uuid") String uuid) {
        User user = getByUuid(uuid);
        return prevnamesService.findByUuid(user.getUuid());
    }

    @GetMapping("/api/all")
    public Iterable<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/api/names/all")
    public Iterable<PrevNames> getAllNames() {
        return prevnamesService.findAllNames();
    }

    @GetMapping("/api/count")
    public long count() {
        return userService.count();
    }

    @GetMapping("/api/names/count")
    public long countNames() {
        return prevnamesService.count();
    }

    @Controller
    public static class UserApiHelpController {

        @GetMapping({"/api", "/"})
        public String greeting() {
            //public String greeting(Model model) {
            //model.addAttribute("uuid", uuid);
            return "ApiHelp";
        }
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