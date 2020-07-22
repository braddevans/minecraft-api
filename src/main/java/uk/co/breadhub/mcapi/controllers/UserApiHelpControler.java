package uk.co.breadhub.mcapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserApiHelpControler {

    @GetMapping("/api")
    public String greeting() {
        //public String greeting(Model model) {
        //model.addAttribute("uuid", uuid);
        return "ApiHelp";
    }
}
