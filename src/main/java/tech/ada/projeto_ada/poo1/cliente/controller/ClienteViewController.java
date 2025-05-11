package tech.ada.projeto_ada.poo1.cliente.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.ada.projeto_ada.poo1.cliente.dto.ClienteResponseDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.CriarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.mapper.CriarClienteRequestMapper;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.BuscarClienteService;
import tech.ada.projeto_ada.poo1.cliente.service.CriarClienteService;
import tech.ada.projeto_ada.usuario.dto.mapper.CriarUsuarioRequestMapper;

import java.util.List;

@Controller
@RequestMapping("poo1/cliente")
public class ClienteViewController {

    private final BuscarClienteService buscarClienteService;
    private final CriarClienteService criarClienteService;

    public ClienteViewController(BuscarClienteService buscarClienteService, CriarClienteService criarClienteService) {
        this.buscarClienteService = buscarClienteService;
        this.criarClienteService = criarClienteService;
    }

    @GetMapping("/listar")
    public String listarClientes(Model model) {
        List<ClienteResponseDTO> clientes = buscarClienteService.buscarTodosClientes();
        model.addAttribute("clientes", clientes);
        return "poo1/cliente/listar";
    }

    @GetMapping("/cadastrar")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("cliente", new CriarClienteRequestDTO());
        return "poo1/cliente/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute("cliente") @Valid CriarClienteRequestDTO novoClienteRequestDTO,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "poo1/cliente/cadastrar";
        }

        Cliente novoCliente = CriarClienteRequestMapper.toEntity(novoClienteRequestDTO);
        criarClienteService.criarCliente(novoCliente);
        return "redirect:/poo1/cliente/listar";
    }
}
