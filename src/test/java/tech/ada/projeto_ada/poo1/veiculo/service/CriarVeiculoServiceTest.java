package tech.ada.projeto_ada.poo1.veiculo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;
import tech.ada.projeto_ada.poo1.veiculo.util.TestVeiculoPrinter;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CriarVeiculoServiceTest {
    CriarVeiculoService service;
    VeiculoRepository repository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        repository = Mockito.mock(VeiculoRepository.class);
        service = new CriarVeiculoService(repository);
    }

    @Test
    void deveCriarVeiculoComSucesso() {
        Veiculo veiculo = VeiculoCreator.criarCarroComum();

        Mockito.when(repository.save(veiculo)).thenReturn(veiculo);

        Veiculo veiculoCriado = service.criarVeiculo(veiculo);

        assertNotNull(veiculoCriado);
        assertEquals(veiculo.getModelo(), veiculoCriado.getModelo());
        assertEquals(veiculo.getPlaca(), veiculoCriado.getPlaca());
        assertEquals(veiculo.getDisponivel(), veiculoCriado.getDisponivel());
        assertEquals(veiculo.getTipo(), veiculoCriado.getTipo());

        Mockito.verify(repository, Mockito.times(1)).save(veiculo);

        TestVeiculoPrinter.printVeiculo("Veículo criado", veiculoCriado);
    }

}