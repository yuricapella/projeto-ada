package tech.ada.projeto_ada.poo1.cliente.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;

@Service
public class CriarClienteService {
    private final ClienteRepository repository;

    public CriarClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente criarCliente(Cliente cliente) {
        return repository.save(cliente);
    }
}
