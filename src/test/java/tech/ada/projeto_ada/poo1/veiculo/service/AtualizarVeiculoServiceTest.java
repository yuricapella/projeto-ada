package tech.ada.projeto_ada.poo1.veiculo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.veiculo.dto.AtualizarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoNaoEncontradoException;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;
import tech.ada.projeto_ada.poo1.veiculo.util.TestVeiculoPrinter;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.junit.jupiter.api.Assertions.*;

class AtualizarVeiculoServiceTest {
    AtualizarVeiculoService atualizarVeiculoService;
    BuscarVeiculoService buscarVeiculoService;
    VeiculoRepository repository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        repository = Mockito.mock(VeiculoRepository.class);
        buscarVeiculoService = Mockito.mock(BuscarVeiculoService.class);
        atualizarVeiculoService = new AtualizarVeiculoService(buscarVeiculoService, repository);
    }

    @Test
    void deveAtualizarVeiculoPorIdComSucesso() {
        Long id = 1L;
        Veiculo veiculoExistente = VeiculoCreator.criarCarroComum();
        AtualizarVeiculoRequestDTO veiculoDTO = new AtualizarVeiculoRequestDTO(
                "Novo Modelo",
                "NEW1234",
                500.0,
                true,
                TipoVeiculo.LUXO
        );

        Mockito.when(buscarVeiculoService.buscarVeiculoPorId(id))
                .thenReturn(veiculoExistente);

        atualizarVeiculoService.atualizar(veiculoDTO, id);

        assertNotNull(veiculoExistente);
        assertEquals(veiculoDTO.getModelo(), veiculoExistente.getModelo());
        assertEquals(veiculoDTO.getPlaca(), veiculoExistente.getPlaca());
        assertEquals(veiculoDTO.getDisponivel(), veiculoExistente.getDisponivel());
        assertEquals(veiculoDTO.getTipo(), veiculoExistente.getTipo());

        Mockito.verify(buscarVeiculoService, Mockito.times(1)).buscarVeiculoPorId(id);
        Mockito.verify(repository, Mockito.times(1)).save(veiculoExistente);

        TestVeiculoPrinter.printVeiculo("Veículo atualizado", veiculoExistente);
    }

    @Test
    void deveLancarExcecaoQuandoVeiculoNaoEncontrado() {
        Long id = 1L;
        AtualizarVeiculoRequestDTO veiculoDTO = new AtualizarVeiculoRequestDTO(
                "Novo Modelo",
                "NEW1234",
                500.0,
                true,
                TipoVeiculo.LUXO
        );

        Mockito.when(buscarVeiculoService.buscarVeiculoPorId(id))
                .thenThrow(new VeiculoNaoEncontradoException(id));

        VeiculoNaoEncontradoException exception = assertThrows(
                VeiculoNaoEncontradoException.class,
                () -> atualizarVeiculoService.atualizar(veiculoDTO, id)
        );

        assertEquals("Veiculo com id " + id + " não encontrado.", exception.getMessage());
        Mockito.verify(buscarVeiculoService, Mockito.times(1)).buscarVeiculoPorId(id);

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }

}