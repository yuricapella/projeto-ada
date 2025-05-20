package tech.ada.projeto_ada.poo1.veiculo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.InOrder;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoNaoEncontradoException;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;
import tech.ada.projeto_ada.poo1.veiculo.util.TestVeiculoPrinter;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeletarVeiculoServiceTest {
    DeletarVeiculoService deletarService;
    BuscarVeiculoService buscarService;
    VeiculoRepository repository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        repository = Mockito.mock(VeiculoRepository.class);
        buscarService = Mockito.mock(BuscarVeiculoService.class);
        deletarService = new DeletarVeiculoService(repository, buscarService);
    }

    @Test
    void deveDeletarUmVeiculoPorIdComSucesso() {
        Long id = 1L;
        Veiculo veiculo = VeiculoCreator.criarCarroComum();

        Mockito.when(buscarService.buscarVeiculoPorId(id)).thenReturn(veiculo);

        deletarService.deletarVeiculo(id);

        Mockito.verify(buscarService, Mockito.times(1)).buscarVeiculoPorId(id);
        Mockito.verify(repository, Mockito.times(1)).deleteById(id);

        InOrder inOrder = Mockito.inOrder(buscarService, repository);
        inOrder.verify(buscarService, Mockito.times(1)).buscarVeiculoPorId(id);
        inOrder.verify(repository, Mockito.times(1)).deleteById(id);

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

}