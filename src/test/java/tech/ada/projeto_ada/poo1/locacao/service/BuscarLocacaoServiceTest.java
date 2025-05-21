package tech.ada.projeto_ada.poo1.locacao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.locacao.exception.LocacaoNaoEncontradaException;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.poo1.locacao.util.LocacaoCreator;
import tech.ada.projeto_ada.poo1.locacao.util.TestLocacaoPrinter;
import tech.ada.projeto_ada.util.TestPrinter;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BuscarLocacaoServiceTest {

    BuscarLocacaoService service;
    LocacaoRepository locacaoRepository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        locacaoRepository = Mockito.mock(LocacaoRepository.class);
        service = new BuscarLocacaoService(locacaoRepository);
    }

    @Test
    void deveBuscarTodasAsLocacoesComSucesso() {
        List<Locacao> locacoes = LocacaoCreator.criarLocacoes(3);

        Mockito.when(locacaoRepository.findAll()).thenReturn(locacoes);

        List<Locacao> locacoesEncontradas = service.buscarTodasAsLocacoes();

        assertNotNull(locacoesEncontradas);
        assertEquals(locacoes.size(), locacoesEncontradas.size());
        Mockito.verify(locacaoRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deveBuscarLocacaoPorIdComSucesso() {
        Long id = 1L;
        Locacao locacao = LocacaoCreator.criarLocacao();

        Mockito.when(locacaoRepository.findById(id)).thenReturn(Optional.of(locacao));

        Locacao locacaoEncontrada = service.buscarLocacaoPorId(id);

        assertNotNull(locacaoEncontrada);
        assertEquals(locacao, locacaoEncontrada);
        Mockito.verify(locacaoRepository, Mockito.times(1)).findById(id);

        TestLocacaoPrinter.printLocacao("Locação encontrada:", locacaoEncontrada);
    }

    @Test
    void deveLancarExcecaoQuandoLocacaoNaoEncontrada() {
        Long id = 1L;

        Mockito.when(locacaoRepository.findById(id)).thenReturn(Optional.empty());

        LocacaoNaoEncontradaException exception = assertThrows(LocacaoNaoEncontradaException.class, () -> {
            service.buscarLocacaoPorId(id);
        });

        assertNotNull(exception);
        assertEquals("Locação com id " + id + " não encontrada.", exception.getMessage());
        Mockito.verify(locacaoRepository, Mockito.times(1)).findById(id);

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }
}
