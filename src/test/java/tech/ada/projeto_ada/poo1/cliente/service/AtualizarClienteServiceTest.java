package tech.ada.projeto_ada.poo1.cliente.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.cliente.dto.AtualizarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.exception.ClienteNaoEncontradoException;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;
import tech.ada.projeto_ada.poo1.cliente.util.ClienteCreator;
import tech.ada.projeto_ada.poo1.cliente.util.TestClientPrinter;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.junit.jupiter.api.Assertions.*;

class AtualizarClienteServiceTest {

    AtualizarClienteService atualizarClienteService;
    BuscarClienteService buscarClienteService;
    ClienteRepository clienteRepository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        clienteRepository = Mockito.mock(ClienteRepository.class);
        buscarClienteService = Mockito.mock(BuscarClienteService.class);
        atualizarClienteService = new AtualizarClienteService(buscarClienteService, clienteRepository);
    }

    @Test
    void deveAtualizarUmUsuarioPorIdComSucesso(){
        Long id = 1L;
        Cliente clienteExistente = ClienteCreator.criarCliente();
        AtualizarClienteRequestDTO clienteAtualizado = new AtualizarClienteRequestDTO("Novo Nome","CPF" ,"Novo Endereço", "Novo Telefone");

        Mockito.when(buscarClienteService.buscarClientePorId(id)).thenReturn(clienteExistente);

        atualizarClienteService.atualizar(clienteAtualizado, id);

        assertNotNull(clienteExistente);
        Mockito.verify(buscarClienteService, Mockito.times(1)).buscarClientePorId(id);
        Mockito.verify(clienteRepository, Mockito.times(1)).save(clienteExistente);

        TestClientPrinter.printCliente("Cliente atualizado", clienteExistente);
    }

    @Test
    void deveLancarExcessaoQuandoUsuarioNaoEncontrado(){
        Long id = 1L;
        AtualizarClienteRequestDTO clienteAtualizado = new AtualizarClienteRequestDTO("Novo Nome","CPF" ,"Novo Endereço", "Novo Telefone");

        Mockito.when(buscarClienteService.buscarClientePorId(id)).thenThrow(new ClienteNaoEncontradoException(id));

        ClienteNaoEncontradoException exception = assertThrows(ClienteNaoEncontradoException.class, () -> {
            atualizarClienteService.atualizar(clienteAtualizado, id);
        });

        assertNotNull(exception);
        assertEquals("Cliente com id " + id + " não encontrado.", exception.getMessage());
        Mockito.verify(buscarClienteService, Mockito.times(1)).buscarClientePorId(id);

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }
}