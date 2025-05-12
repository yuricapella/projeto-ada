package tech.ada.projeto_ada.poo1.cliente.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.exception.ClienteNaoEncontradoException;
import tech.ada.projeto_ada.poo1.cliente.dto.ClienteResponseDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.mapper.ClienteResponseMapper;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuscarClienteService {
    private final ClienteRepository repository;

    public BuscarClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> buscarTodosClientes() {
        return repository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        return clienteOptional
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }
}
