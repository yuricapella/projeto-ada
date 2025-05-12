package tech.ada.projeto_ada.poo1.cliente.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.cliente.dto.AtualizarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.mapper.AtualizarClienteRequestMapper;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;

@Service
public class AtualizarClienteService {
    private final BuscarClienteService buscarClienteService;
    private final ClienteRepository repository;


    public AtualizarClienteService(BuscarClienteService buscarClienteService, ClienteRepository repository) {
        this.buscarClienteService = buscarClienteService;
        this.repository = repository;
    }

    public void atualizar(AtualizarClienteRequestDTO clienteAtualizado, Long id) {
        Cliente clienteExistente = buscarClienteService.buscarClientePorId(id);
        AtualizarClienteRequestMapper.updateEntity(clienteExistente, clienteAtualizado);
        repository.save(clienteExistente);
    }
}
