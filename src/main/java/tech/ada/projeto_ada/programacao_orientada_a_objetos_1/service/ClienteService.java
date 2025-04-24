package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Cliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.TipoCliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
    
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
    
    public List<Cliente> listarPorTipo(TipoCliente tipo) {
        return clienteRepository.findByTipoCliente(tipo);
    }
    
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public void inicializarClientes() {
        if (clienteRepository.count() == 0) {
            // Criar clientes iniciais
            Cliente cliente1 = new Cliente("Nome 1", TipoCliente.PESSOA_FISICA, "Rua tal 1", "00 90000-0000");
            Cliente cliente2 = new Cliente("Nome 2", TipoCliente.PESSOA_FISICA, "Rua tal 2", "00 90000-0000");
            Cliente cliente3 = new Cliente("Empresa 1", TipoCliente.PESSOA_JURIDICA, "Rua tal 3", "00 90000-0000");
            Cliente cliente4 = new Cliente("Empresa 2", TipoCliente.PESSOA_JURIDICA, "Rua tal 4", "00 90000-0000");
            
            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);
            clienteRepository.save(cliente3);
            clienteRepository.save(cliente4);
        }
    }
}