package tech.ada.projeto_ada.poo1.cliente.service;

import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;

public class DeletarClienteService {
    private final ClienteRepository repository;
    private final BuscarClienteService buscarClienteService;

    public DeletarClienteService(ClienteRepository repository, BuscarClienteService buscarClienteService) {
        this.repository = repository;
        this.buscarClienteService = buscarClienteService;
    }

    public void deletarCliente(Long id) {
        buscarClienteService.buscarClientePorId(id);
        repository.deleteById(id);
    }

}
