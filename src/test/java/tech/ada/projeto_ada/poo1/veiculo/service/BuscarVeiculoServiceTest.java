package tech.ada.projeto_ada.poo1.veiculo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoNaoEncontradoException;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;
import tech.ada.projeto_ada.poo1.veiculo.util.TestVeiculoPrinter;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;
import tech.ada.projeto_ada.util.TestPrinter;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BuscarVeiculoServiceTest {
    BuscarVeiculoService service;
    VeiculoRepository repository;

    @BeforeEach
    void setup(TestInfo testInfo){
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        repository = Mockito.mock(VeiculoRepository.class);
        service = new BuscarVeiculoService(repository);
    }

    @Test
    void deveBuscarTodosOsVeiculosComSucesso() {
        List<Veiculo> veiculos = VeiculoCreator.criarVeiculos(1,1,1,1,1);

        Mockito.when(repository.findAll()).thenReturn(veiculos);

        List<Veiculo> veiculosEncontrados = service.buscarTodosVeiculos();

        assertNotNull(veiculosEncontrados);
        assertEquals(veiculos.size(), veiculosEncontrados.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();

        TestVeiculoPrinter.printVeiculos("Veículos encontrados", veiculosEncontrados);
    }

    @Test
    void deveBuscarVeiculoPorIdComSucesso() {
        Long id = 1L;
        Veiculo veiculo = VeiculoCreator.criarCarroComum();

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(veiculo));

        Veiculo veiculoEncontrado = service.buscarVeiculoPorId(id);

        assertNotNull(veiculoEncontrado);
        assertEquals(veiculo, veiculoEncontrado);
        Mockito.verify(repository, Mockito.times(1)).findById(id);

        TestVeiculoPrinter.printVeiculo("Veículo encontrado", veiculoEncontrado);
    }

    @Test
    void deveLancarExcecaoQuandoVeiculoNaoEncontrado() {
        Long id = 1L;

        Mockito.when(repository.findById(id)).thenThrow(new VeiculoNaoEncontradoException(id));

        VeiculoNaoEncontradoException exception = assertThrows(
                VeiculoNaoEncontradoException.class,
                () -> service.buscarVeiculoPorId(id)
        );

        assertNotNull(exception);
        assertEquals("Veiculo com id " + id + " não encontrado.", exception.getMessage());
        Mockito.verify(repository, Mockito.times(1)).findById(id);

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }



}