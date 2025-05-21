package tech.ada.projeto_ada.poo1.cliente.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.cliente.exception.ClienteNaoEncontradoException;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;
import tech.ada.projeto_ada.poo1.cliente.util.ClienteCreator;
import tech.ada.projeto_ada.poo1.cliente.util.TestClientPrinter;
import tech.ada.projeto_ada.util.TestPrinter;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BuscarClienteServiceTest {
    BuscarClienteService service;
    ClienteRepository clienteRepository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        clienteRepository = Mockito.mock(ClienteRepository.class);
        service = new BuscarClienteService(clienteRepository);
    }

    @Test
    void deveBuscarTodosOsClienteComSucesso(){
        List<Cliente> clientes = ClienteCreator.criarClientes(3);

        Mockito.when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> clientesEncontrados = service.buscarTodosClientes();

        assertNotNull(clientesEncontrados);
        assertEquals(clientes.size(), clientesEncontrados.size());
        Mockito.verify(clienteRepository, Mockito.times(1)).findAll();

        TestClientPrinter.printClientes("Clientes encontrados", clientesEncontrados);
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso(){
        Long id = 1L;
        Cliente cliente = ClienteCreator.criarCliente();

        Mockito.when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente clienteEncontrado = service.buscarClientePorId(id);

        assertNotNull(clienteEncontrado);
        assertEquals(cliente, clienteEncontrado);
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(id);

        TestClientPrinter.printCliente("Cliente encontrado", clienteEncontrado);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado(){
        Long id = 1L;

        Mockito.when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        ClienteNaoEncontradoException exception = assertThrows(
                ClienteNaoEncontradoException.class, () -> service.buscarClientePorId(id));

        assertNotNull(exception);
        assertEquals("Cliente com id " + id + " não encontrado.",exception.getMessage());
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(id);

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }


}