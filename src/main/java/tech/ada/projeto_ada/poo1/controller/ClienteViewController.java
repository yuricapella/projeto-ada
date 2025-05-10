package tech.ada.projeto_ada.poo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.ada.projeto_ada.poo1.dto.ClienteResponseDTO;
import tech.ada.projeto_ada.poo1.service.cliente.BuscarClienteService;

import java.util.List;

@Controller
@RequestMapping("poo1/clientes")
public class ClienteViewController {

    private final BuscarClienteService buscarClienteService;

    public ClienteViewController(BuscarClienteService buscarClienteService) {
        this.buscarClienteService = buscarClienteService;
    }

    @GetMapping
    public String listarClientes(Model model) {
        List<ClienteResponseDTO> clientes = buscarClienteService.buscarTodosClientes();
        model.addAttribute("clientes", clientes);
        return "poo1/clientes";
    }
}
