package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service.ClienteService;

@Controller
@RequestMapping("/poo1/clientes")
public class ClienteController {
    
    private final ClienteService clienteService;
    
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "poo1/cliente/list";
    }
}