package tech.ada.projeto_ada.poo1.locacao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.InOrder;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.BuscarClienteService;
import tech.ada.projeto_ada.poo1.cliente.util.ClienteCreator;
import tech.ada.projeto_ada.poo1.locacao.dto.AtualizarLocacaoRequestDTO;
import tech.ada.projeto_ada.poo1.locacao.exception.LocacaoNaoEncontradaException;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.poo1.locacao.util.LocacaoCreator;
import tech.ada.projeto_ada.poo1.locacao.util.TestLocacaoPrinter;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.service.BuscarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AtualizarLocacaoServiceTest {

    private AtualizarLocacaoService service;
    private BuscarLocacaoService buscarLocacaoService;
    private BuscarClienteService buscarClienteService;
    private BuscarVeiculoService buscarVeiculoService;
    private LocacaoRepository repository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        buscarLocacaoService = mock(BuscarLocacaoService.class);
        buscarClienteService = mock(BuscarClienteService.class);
        buscarVeiculoService = mock(BuscarVeiculoService.class);
        repository          = mock(LocacaoRepository.class);
        service = new AtualizarLocacaoService(repository,
                buscarLocacaoService, buscarClienteService, buscarVeiculoService);
    }

    @Test
    void deveAtualizarLocacaoComSucesso() {
        Long id = 1L;

        Locacao existente = LocacaoCreator.criarLocacao();
        when(buscarLocacaoService.buscarLocacaoPorId(id)).thenReturn(existente);

        AtualizarLocacaoRequestDTO dto = new AtualizarLocacaoRequestDTO();
        dto.setClienteId(100L);
        dto.setVeiculoId(200L);
        dto.setDiasDeLocacao(7);

        Cliente novoCliente = ClienteCreator.criarCliente();
        novoCliente.setId(100L);
        when(buscarClienteService.buscarClientePorId(100L)).thenReturn(novoCliente);

        Veiculo novoVeiculo = VeiculoCreator.criarCarroComum();
        novoVeiculo.setId(200L);
        when(buscarVeiculoService.buscarVeiculoPorId(200L)).thenReturn(novoVeiculo);

        when(repository.save(existente)).thenReturn(existente);

        service.atualizar(dto, id);

        InOrder ordem = inOrder(buscarLocacaoService, buscarClienteService, buscarVeiculoService, repository);
        ordem.verify(buscarLocacaoService).buscarLocacaoPorId(id);
        ordem.verify(buscarClienteService).buscarClientePorId(100L);
        ordem.verify(buscarVeiculoService).buscarVeiculoPorId(200L);
        ordem.verify(repository).save(existente);

        assertEquals(novoCliente, existente.getCliente());
        assertEquals(novoVeiculo, existente.getVeiculo());
        assertEquals(7, existente.getDiasDeLocacao());
        assertEquals(existente.getDataCriacao().plusDays(7), existente.getDataFinalizacao());

        TestLocacaoPrinter.printLocacao("Locação atualizada", existente);
    }

    @Test
    void deveLancarExcecaoQuandoLocacaoNaoExistir() {
        Long id = 2L;
        when(buscarLocacaoService.buscarLocacaoPorId(id))
                .thenThrow(new LocacaoNaoEncontradaException(id));

        AtualizarLocacaoRequestDTO dto = new AtualizarLocacaoRequestDTO();

        LocacaoNaoEncontradaException ex = assertThrows(
                LocacaoNaoEncontradaException.class,
                () -> service.atualizar(dto, id)
        );

        assertEquals("Locação com id " + id + " não encontrada.", ex.getMessage());
        verify(buscarLocacaoService, times(1)).buscarLocacaoPorId(id);
        verify(repository, never()).save(any());
        TestPrinter.printMensagemDeErro(ex.getMessage());
    }
}
