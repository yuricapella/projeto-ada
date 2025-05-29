package tech.ada.projeto_ada.poo1.cliente.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.ada.projeto_ada.poo1.cliente.dto.AtualizarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.CriarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.mapper.CriarClienteRequestMapper;
import tech.ada.projeto_ada.poo1.cliente.exception.ClienteComLocacaoException;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.AtualizarClienteService;
import tech.ada.projeto_ada.poo1.cliente.service.BuscarClienteService;
import tech.ada.projeto_ada.poo1.cliente.service.CriarClienteService;
import tech.ada.projeto_ada.poo1.cliente.service.DeletarClienteService;

import java.util.List;

@Controller
@RequestMapping("poo1/cliente")
public class ClienteViewController {

    private final BuscarClienteService buscarClienteService;
    private final CriarClienteService criarClienteService;
    private final AtualizarClienteService atualizarClienteService;
    private final DeletarClienteService deletarClienteService;

    public ClienteViewController(BuscarClienteService buscarClienteService, CriarClienteService criarClienteService, AtualizarClienteService atualizarClienteService, DeletarClienteService deletarClienteService) {
        this.buscarClienteService = buscarClienteService;
        this.criarClienteService = criarClienteService;
        this.atualizarClienteService = atualizarClienteService;
        this.deletarClienteService = deletarClienteService;
    }

    @GetMapping("/listar")
    public String listarClientes(Model model) {
        List<Cliente> clientes = buscarClienteService.buscarTodosClientes();
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

    @GetMapping("/atualizar/{id}")
    public String mostrarFormularioAtualizacao(@PathVariable Long id, Model model) {
        Cliente cliente = buscarClienteService.buscarClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "poo1/cliente/atualizar";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarCliente(
            @ModelAttribute("cliente") @Valid AtualizarClienteRequestDTO clienteAtualizado,
            BindingResult bindingResult,
            @PathVariable Long id,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "poo1/cliente/atualizar";
        }
        atualizarClienteService.atualizar(clienteAtualizado, id);
        return "redirect:/poo1/cliente/listar";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id, Model model) {
        try {
            deletarClienteService.deletarCliente(id);
            return "redirect:/poo1/cliente/listar";
        } catch (ClienteComLocacaoException ex) {
            List<Cliente> clientes = buscarClienteService.buscarTodosClientes();
            model.addAttribute("clientes", clientes);
            model.addAttribute("erroClienteComLocacao", ex.getMessage());
            return "poo1/cliente/listar";
        }
    }
}
