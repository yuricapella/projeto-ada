package tech.ada.projeto_ada.poo1.cliente.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;
import tech.ada.projeto_ada.poo1.cliente.util.ClienteCreator;
import tech.ada.projeto_ada.poo1.cliente.util.TestClientPrinter;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CriarClienteServiceTest {
    CriarClienteService criarClienteService;
    ClienteRepository clienteRepository;
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        clienteRepository = Mockito.mock(ClienteRepository.class);
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
        criarClienteService = new CriarClienteService(clienteRepository, usuarioRepository);
    }

    @Test
    void DeveCriarClienteComSucesso(){
        Cliente cliente = ClienteCreator.criarCliente();

        Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente clienteCriado = criarClienteService.criarCliente(cliente);

        assertNotNull(clienteCriado);
        assertEquals(cliente.getNome(), clienteCriado.getNome());
        assertEquals(cliente.getDocumento(), clienteCriado.getDocumento());
        assertEquals(cliente.getEndereco(), clienteCriado.getEndereco());
        assertEquals(cliente.getTelefone(), clienteCriado.getTelefone());
        assertEquals(cliente.getDataCriacao(), clienteCriado.getDataCriacao());
        assertEquals(cliente.getDataAtualizacao(), clienteCriado.getDataAtualizacao());
        assertEquals(cliente.getId(), clienteCriado.getId());
        Mockito.verify(clienteRepository, Mockito.times(1)).save(cliente);

        TestClientPrinter.printCliente("Cliente criado", clienteCriado);
    }
}