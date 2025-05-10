package tech.ada.projeto_ada.poo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.projeto_ada.poo1.dto.ClienteResponseDTO;
import tech.ada.projeto_ada.poo1.service.cliente.BuscarClienteService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteApiController {
    private final BuscarClienteService buscarClienteService;

    public ClienteApiController(BuscarClienteService buscarClienteService) {
        this.buscarClienteService = buscarClienteService;
    }

    @GetMapping
    public List<ClienteResponseDTO> buscarTodosClientes(){
        return buscarClienteService.buscarTodosClientes();
    }
}
