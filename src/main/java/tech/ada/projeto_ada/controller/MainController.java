package tech.ada.projeto_ada.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro/cadastro";
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public String home() {
        return "home";
    }
}
