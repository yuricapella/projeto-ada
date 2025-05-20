package tech.ada.projeto_ada.poo1.veiculo.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.CarroComum;
import tech.ada.projeto_ada.poo1.veiculo.util.TestVeiculoPrinter;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;
import tech.ada.projeto_ada.util.TestPrinter;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@DataJpaTest
class VeiculoRepositoryTest {

    @Autowired
    VeiculoRepository repository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        List<Veiculo> veiculos = VeiculoCreator.criarVeiculos(3, 0, 0, 0, 0);
        repository.saveAll(veiculos);
    }

    @Test
    void deveSalvarUmVeiculoValidoComSucesso() {
        CarroComum carro = (CarroComum) VeiculoCreator.criarCarroComum();
        carro.setModelo("Modelo Teste");
        carro.setPlaca("TEST1234");

        Veiculo salvo = repository.save(carro);
        Optional<Veiculo> encontrado = repository.findById(salvo.getId());

        assertNotNull(salvo);
        assertTrue(encontrado.isPresent());
        assertEquals("Modelo Teste", encontrado.get().getModelo());

        TestVeiculoPrinter.printVeiculo("Veículo salvo", salvo);
        TestVeiculoPrinter.printVeiculo("Veículo encontrado", encontrado.get());
    }

    @Test
    void deveLancarExcecaoQuandoConstraintForViolada() {
        CarroComum carro = (CarroComum) VeiculoCreator.criarCarroComum();
        carro.setPlaca(null);

        DataIntegrityViolationException exception = assertThrows(
                DataIntegrityViolationException.class,
                () -> repository.save(carro)
        );

        Assertions.assertNotNull(exception);
    }

    @Test
    void deveBuscarTodosOsVeiculosComSucesso() {
        List<Veiculo> todos = repository.findAll();

        assertNotNull(todos);
        assertEquals(3, todos.size());

        TestVeiculoPrinter.printVeiculos("Veículos encontrados", todos);
    }

    @Test
    void deveAtualizarUmVeiculoValidoComSucesso() {
        Veiculo veiculoExistente = repository.findAll().get(0);
        veiculoExistente.setModelo("Atualizado");
        veiculoExistente.setPlaca("UPD1234");

        Veiculo veiculoAtualizado = repository.save(veiculoExistente);
        Optional<Veiculo> veiculoRecuperado = repository.findById(veiculoAtualizado.getId());

        assertTrue(veiculoRecuperado.isPresent());
        assertEquals("Atualizado", veiculoRecuperado.get().getModelo());
        assertEquals("UPD1234", veiculoRecuperado.get().getPlaca());

        TestVeiculoPrinter.printVeiculo("Veículo atualizado", veiculoRecuperado.get());
    }

    @Test
    void deveDeletarUmVeiculoComSucesso() {
        Veiculo veiculoExistente = repository.findAll().get(0);
        Long id = veiculoExistente.getId();

        repository.deleteById(id);

        Optional<Veiculo> veiculoDeletado = repository.findById(id);
        boolean exists = repository.existsById(id);

        assertAll(
                () -> assertTrue(veiculoDeletado.isEmpty()),
                () -> assertFalse(exists)
        );
    }
}
