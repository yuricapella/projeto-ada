package tech.ada.projeto_ada.poo1.cliente.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.cliente.exception.ClienteComLocacaoException;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;

@Service
public class DeletarClienteService {
    private final ClienteRepository repository;
    private final BuscarClienteService buscarClienteService;
    private final LocacaoRepository locacaoRepository;


    public DeletarClienteService(ClienteRepository repository, BuscarClienteService buscarClienteService, LocacaoRepository locacaoRepository) {
        this.repository = repository;
        this.buscarClienteService = buscarClienteService;
        this.locacaoRepository = locacaoRepository;
    }

    public void deletarCliente(Long id) {
        buscarClienteService.buscarClientePorId(id);
        if (locacaoRepository.existsByClienteId(id)) {
            throw new ClienteComLocacaoException(id);
        }
        repository.deleteById(id);
    }

}
