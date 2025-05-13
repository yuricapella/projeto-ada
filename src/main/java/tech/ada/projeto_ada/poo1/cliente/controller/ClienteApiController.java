package tech.ada.projeto_ada.poo1.cliente.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.projeto_ada.poo1.cliente.dto.CriarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.AtualizarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.ClienteResponseDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.mapper.CriarClienteRequestMapper;
import tech.ada.projeto_ada.poo1.cliente.dto.mapper.ClienteResponseMapper;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.*;

import java.util.List;

@RestController
@RequestMapping("/api/poo1/clientes")
public class ClienteApiController {

    private final BuscarClienteService buscarService;
    private final CriarClienteService criarService;
    private final AtualizarClienteService atualizarService;
    private final DeletarClienteService deletarService;

    public ClienteApiController(
            BuscarClienteService buscarService,
            CriarClienteService criarService,
            AtualizarClienteService atualizarService,
            DeletarClienteService deletarService
    ) {
        this.buscarService = buscarService;
        this.criarService = criarService;
        this.atualizarService = atualizarService;
        this.deletarService = deletarService;
    }

    @GetMapping
    public List<ClienteResponseDTO> listarTodos() {
        return buscarService.buscarTodosClientes()
                .stream()
                .map(ClienteResponseMapper::toClienteDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        Cliente cliente = buscarService.buscarClientePorId(id);
        return ResponseEntity.ok(ClienteResponseMapper.toClienteDTO(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(
            @RequestBody @Valid CriarClienteRequestDTO dto
    ) {
        Cliente salvo = criarService.criarCliente(CriarClienteRequestMapper.toEntity(dto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ClienteResponseMapper.toClienteDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarClienteRequestDTO dto
    ) {
        atualizarService.atualizar(dto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
