package tech.ada.projeto_ada.poo1.locacao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.BuscarClienteService;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.poo1.locacao.util.TestLocacaoPrinter;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoIndisponivelException;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.service.BuscarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;
import tech.ada.projeto_ada.util.TestPrinter;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CriarLocacaoServiceTest {

    CriarLocacaoService criarLocacaoService;
    LocacaoRepository locacaoRepository;
    BuscarVeiculoService buscarVeiculoService;
    BuscarClienteService buscarClienteService;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());

        locacaoRepository = Mockito.mock(LocacaoRepository.class);
        buscarVeiculoService = Mockito.mock(BuscarVeiculoService.class);
        buscarClienteService = Mockito.mock(BuscarClienteService.class);

        criarLocacaoService = new CriarLocacaoService(locacaoRepository, buscarVeiculoService, buscarClienteService);
    }

    @Test
    void deveCriarLocacaoComSucesso() {
        Long veiculoId = 10L;
        Long clienteId = 20L;
        Integer dias = 4;

        Veiculo veiculo = VeiculoCreator.criarCarroComum();
        veiculo.setId(veiculoId);
        veiculo.setDisponivel(true);
        veiculo.setValorDiaria(320.0);

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);

        Mockito.when(buscarVeiculoService.buscarVeiculoPorId(veiculoId)).thenReturn(veiculo);
        Mockito.when(buscarClienteService.buscarClientePorId(clienteId)).thenReturn(cliente);
        Mockito.when(locacaoRepository.save(Mockito.any(Locacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Locacao locacao = criarLocacaoService.criarLocacao(veiculoId, clienteId, dias);

        assertNotNull(locacao);
        assertEquals(cliente, locacao.getCliente());
        assertEquals(veiculo, locacao.getVeiculo());
        assertEquals(dias, locacao.getDiasDeLocacao());
        assertEquals(320.0 * dias, locacao.getPrecoTotalLocacao());

        LocalDateTime esperadoFinal = locacao.getDataCriacao().plusDays(dias);
        assertEquals(esperadoFinal, locacao.getDataFinalizacao());

        Mockito.verify(buscarVeiculoService, Mockito.times(1)).buscarVeiculoPorId(veiculoId);
        Mockito.verify(buscarClienteService, Mockito.times(1)).buscarClientePorId(clienteId);
        Mockito.verify(locacaoRepository, Mockito.times(1)).save(Mockito.any(Locacao.class));

        TestLocacaoPrinter.printLocacao("Locacao criada",locacao);
    }

    @Test
    void deveLancarExcecaoQuandoVeiculoIndisponivel() {
        Long veiculoId = 5L;
        Long clienteId = 6L;
        Integer dias = 2;

        Veiculo veiculo = VeiculoCreator.criarCarroComum();
        veiculo.setId(veiculoId);
        veiculo.setDisponivel(false);

        Mockito.when(buscarVeiculoService.buscarVeiculoPorId(veiculoId)).thenReturn(veiculo);

        VeiculoIndisponivelException exception = assertThrows(VeiculoIndisponivelException.class,
                () -> criarLocacaoService.criarLocacao(veiculoId, clienteId, dias));

        Mockito.verify(buscarVeiculoService, Mockito.times(1)).buscarVeiculoPorId(veiculoId);
        Mockito.verify(buscarClienteService, Mockito.never()).buscarClientePorId(Mockito.anyLong());
        Mockito.verify(locacaoRepository, Mockito.never()).save(Mockito.any());

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }
}
