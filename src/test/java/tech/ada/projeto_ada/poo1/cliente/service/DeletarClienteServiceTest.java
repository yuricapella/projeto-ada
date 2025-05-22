package tech.ada.projeto_ada.poo1.cliente.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.InOrder;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.cliente.exception.ClienteComLocacaoException;
import tech.ada.projeto_ada.poo1.cliente.exception.ClienteNaoEncontradoException;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;
import tech.ada.projeto_ada.poo1.cliente.util.TestClientPrinter;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeletarClienteServiceTest {
    DeletarClienteService deletarClienteService;
    BuscarClienteService buscarClienteService;
    ClienteRepository clienteRepository;
    LocacaoRepository locacaoRepository;

    @BeforeEach
    void setUp(TestInfo info) {
        TestPrinter.printInicioDoTeste(info.getDisplayName());
        clienteRepository = Mockito.mock(ClienteRepository.class);
        buscarClienteService = Mockito.mock(BuscarClienteService.class);
        locacaoRepository = Mockito.mock(LocacaoRepository.class);
        deletarClienteService = new DeletarClienteService(clienteRepository, buscarClienteService, locacaoRepository);
    }

    @Test
    void deveDeletarUmClientePorIdComSucesso(){
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);

        deletarClienteService.deletarCliente(id);

        when(buscarClienteService.buscarClientePorId(id)).thenReturn(cliente);
        Mockito.verify(buscarClienteService, Mockito.times(1)).buscarClientePorId(id);
        Mockito.verify(clienteRepository, Mockito.times(1)).deleteById(id);

        InOrder inOrder = Mockito.inOrder(buscarClienteService, clienteRepository);
        inOrder.verify(buscarClienteService, Mockito.times(1)).buscarClientePorId(id);
        inOrder.verify(clienteRepository, Mockito.times(1)).deleteById(id);

        TestClientPrinter.printCliente("Cliente deletado", cliente);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        Long id = 1L;

        when(buscarClienteService.buscarClientePorId(id)).thenThrow(new ClienteNaoEncontradoException(id));

        ClienteNaoEncontradoException exception = assertThrows(ClienteNaoEncontradoException.class, () -> {
            deletarClienteService.deletarCliente(id);
        });

        assertNotNull(exception);
        assertEquals("Cliente com id " + id + " não encontrado.", exception.getMessage());
        Mockito.verify(buscarClienteService, Mockito.times(1)).buscarClientePorId(id);

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }

//    public void deletarCliente(Long id) {
//        buscarClienteService.buscarClientePorId(id);
//        if (locacaoRepository.existsByClienteId(id)) {
//            throw new ClienteComLocacoesException(id);
//        }
//        repository.deleteById(id);
//    }

    @Test
    void deveLancarExcecaoQuandoTentarDeletarClienteComLocacao() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);

        when(buscarClienteService.buscarClientePorId(id)).thenReturn(cliente);
        when(locacaoRepository.existsByClienteId(id)).thenReturn(true);

        ClienteComLocacaoException exception = assertThrows(ClienteComLocacaoException.class, () -> {
            deletarClienteService.deletarCliente(id);
        });

        assertNotNull(exception);
        assertEquals("Não é possível excluir o cliente " + id + " pois existem locações associadas.", exception.getMessage());

        Mockito.verify(buscarClienteService, Mockito.times(1)).buscarClientePorId(id);
        Mockito.verify(locacaoRepository, Mockito.times(1)).existsByClienteId(id);
        Mockito.verify(clienteRepository, Mockito.never()).deleteById(id);

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }



}