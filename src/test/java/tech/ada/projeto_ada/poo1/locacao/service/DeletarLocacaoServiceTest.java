package tech.ada.projeto_ada.poo1.locacao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.locacao.exception.LocacaoNaoEncontradaException;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.poo1.locacao.util.LocacaoCreator;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeletarLocacaoServiceTest {

    DeletarLocacaoService service;
    BuscarLocacaoService buscarLocacaoService;
    LocacaoRepository repository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());

        buscarLocacaoService = Mockito.mock(BuscarLocacaoService.class);
        repository = Mockito.mock(LocacaoRepository.class);

        service = new DeletarLocacaoService(repository,buscarLocacaoService);
    }

    @Test
    void deveDeletarLocacaoComSucesso() {
        Long id = 1L;
        Locacao existente = LocacaoCreator.criarLocacao();

        when(buscarLocacaoService.buscarLocacaoPorId(id)).thenReturn(existente);

        service.deletar(id);

        verify(buscarLocacaoService, times(1)).buscarLocacaoPorId(id);
        verify(repository, times(1)).delete(existente);
    }

    @Test
    void deveLancarExcecaoQuandoLocacaoNaoForEncontrada() {
        Long id = 99L;

        when(buscarLocacaoService.buscarLocacaoPorId(id)).thenThrow(new LocacaoNaoEncontradaException(id));

        LocacaoNaoEncontradaException exception = assertThrows(
                LocacaoNaoEncontradaException.class, () -> service.deletar(id));

        assertEquals("Locação com id " + id + " não encontrada.", exception.getMessage());
        verify(buscarLocacaoService, times(1)).buscarLocacaoPorId(id);
        verify(repository, never()).delete(any());

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }
}
