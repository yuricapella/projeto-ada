package tech.ada.projeto_ada.poo1.veiculo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.InOrder;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoComLocacaoException;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoNaoEncontradoException;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.CarroComum;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;
import tech.ada.projeto_ada.poo1.veiculo.util.TestVeiculoPrinter;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeletarVeiculoServiceTest {
    DeletarVeiculoService deletarService;
    BuscarVeiculoService buscarService;
    VeiculoRepository veiculoRepository;
    LocacaoRepository locacaoRepository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        veiculoRepository = Mockito.mock(VeiculoRepository.class);
        buscarService = Mockito.mock(BuscarVeiculoService.class);
        locacaoRepository = Mockito.mock(LocacaoRepository.class);
        deletarService = new DeletarVeiculoService(veiculoRepository, buscarService, locacaoRepository);
    }

    @Test
    void deveDeletarUmVeiculoPorIdComSucesso() {
        Long id = 1L;
        Veiculo veiculo = VeiculoCreator.criarCarroComum();

        Mockito.when(buscarService.buscarVeiculoPorId(id)).thenReturn(veiculo);

        deletarService.deletarVeiculo(id);

        Mockito.verify(buscarService, Mockito.times(1)).buscarVeiculoPorId(id);
        Mockito.verify(veiculoRepository, Mockito.times(1)).deleteById(id);

        InOrder inOrder = Mockito.inOrder(buscarService, veiculoRepository);
        inOrder.verify(buscarService, Mockito.times(1)).buscarVeiculoPorId(id);
        inOrder.verify(veiculoRepository, Mockito.times(1)).deleteById(id);

        TestVeiculoPrinter.printVeiculo("Veículo deletado", veiculo);
    }


    @Test
    void deveLancarExcessaoQuandoDeletarVeiculoNaoEncontrado(){
        Long id = 1L;

        Mockito.when(buscarService.buscarVeiculoPorId(id)).thenThrow(new VeiculoNaoEncontradoException(id));

        VeiculoNaoEncontradoException exception = Assertions.assertThrows(
                VeiculoNaoEncontradoException.class, () -> deletarService.deletarVeiculo(id));

        Assertions.assertNotNull(exception);
        assertEquals("Veiculo com id " + id + " não encontrado.", exception.getMessage());
        Mockito.verify(buscarService, Mockito.times(1)).buscarVeiculoPorId(id);

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }


    @Test
    void deveLancarExcecaoQuandoTentarDeletarVeiculoComLocacao() {
        Long id = 1L;
        Veiculo veiculo = new CarroComum();
        veiculo.setId(id);

        when(buscarService.buscarVeiculoPorId(id)).thenReturn(veiculo);
        when(locacaoRepository.existsByVeiculoId(id)).thenReturn(true);

        VeiculoComLocacaoException exception = assertThrows(VeiculoComLocacaoException.class, () -> {
            deletarService.deletarVeiculo(id);
        });

        assertNotNull(exception);
        assertEquals("Não é possível excluir o veiculo " + id + " pois existem locações associadas.", exception.getMessage());

        Mockito.verify(buscarService, Mockito.times(1)).buscarVeiculoPorId(id);
        Mockito.verify(locacaoRepository, Mockito.times(1)).existsByVeiculoId(id);
        Mockito.verify(veiculoRepository, Mockito.never()).deleteById(id);

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }

}