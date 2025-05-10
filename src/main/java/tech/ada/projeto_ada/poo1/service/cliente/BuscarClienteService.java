package tech.ada.projeto_ada.poo1.service.cliente;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.exception.ClienteNaoEncontradoException;
import tech.ada.projeto_ada.poo1.dto.ClienteResponseDTO;
import tech.ada.projeto_ada.poo1.dto.mapper.ClienteResponseMapper;
import tech.ada.projeto_ada.poo1.model.Cliente;
import tech.ada.projeto_ada.poo1.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuscarClienteService {
    private final ClienteRepository repository;

    public BuscarClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponseDTO> buscarTodosClientes() {
        return repository.findAll()
                .stream()
                .map(cliente -> ClienteResponseMapper.toClienteDTO(cliente))
                .collect(Collectors.toList());
    }

    public Cliente buscarClientePorId(Long id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        return clienteOptional
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }
}
