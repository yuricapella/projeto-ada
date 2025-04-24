package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Cliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.TipoCliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente clientePF;
    private Cliente clientePJ;

    @BeforeEach
    public void setUp() {
        // Create test clients
        clientePF = mock(Cliente.class);
        when(clientePF.getTipoCliente()).thenReturn(TipoCliente.PESSOA_FISICA);
        when(clientePF.getNome()).thenReturn("João Silva");

        clientePJ = mock(Cliente.class);
        when(clientePJ.getTipoCliente()).thenReturn(TipoCliente.PESSOA_JURIDICA);
        when(clientePJ.getNome()).thenReturn("Empresa XYZ");
    }

    @Test
    public void testListarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(clientePF);
        clientes.add(clientePJ);

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.listarTodos();

        assertEquals(2, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clientePF));
        when(clienteRepository.findById(2L)).thenReturn(Optional.empty());

        Cliente result = clienteService.buscarPorId(1L);
        assertEquals(clientePF, result);

        assertThrows(RuntimeException.class, () -> clienteService.buscarPorId(2L));
    }

    @Test
    public void testListarPorTipo() {
        List<Cliente> clientesPF = new ArrayList<>();
        clientesPF.add(clientePF);

        when(clienteRepository.findByTipoCliente(TipoCliente.PESSOA_FISICA)).thenReturn(clientesPF);

        List<Cliente> result = clienteService.listarPorTipo(TipoCliente.PESSOA_FISICA);

        assertEquals(1, result.size());
        assertEquals(clientePF, result.get(0));
        verify(clienteRepository, times(1)).findByTipoCliente(TipoCliente.PESSOA_FISICA);
    }

    @Test
    public void testSalvar() {
        when(clienteRepository.save(clientePF)).thenReturn(clientePF);

        Cliente result = clienteService.salvar(clientePF);

        assertEquals(clientePF, result);
        verify(clienteRepository, times(1)).save(clientePF);
    }

    @Test
    public void testInicializarClientes() {
        when(clienteRepository.count()).thenReturn(0L);

        clienteService.inicializarClientes();

        // Verify that save was called 4 times (for the 4 default clients)
        verify(clienteRepository, times(4)).save(any(Cliente.class));
    }

    @Test
    public void testInicializarClientesWhenNotEmpty() {
        when(clienteRepository.count()).thenReturn(5L);

        clienteService.inicializarClientes();

        // Verify that save was not called
        verify(clienteRepository, never()).save(any(Cliente.class));
    }
}