package tech.ada.projeto_ada.poo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/poo1")
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "poo1/home";
    }
}