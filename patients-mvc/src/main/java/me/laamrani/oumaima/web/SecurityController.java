package me.laamrani.oumaima.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/notAuthorized")
    public String notAutorized(){
        return "notAuthorized";
    }
}
