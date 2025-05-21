package tech.ada.projeto_ada.poo1.locacao.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;
import tech.ada.projeto_ada.poo1.cliente.util.ClienteCreator;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.util.TestLocacaoPrinter;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;
import tech.ada.projeto_ada.util.TestPrinter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@DataJpaTest
class LocacaoRepositoryTest {

    @Autowired
    LocacaoRepository locacaoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    VeiculoRepository veiculoRepository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
    }

    @Test
    void deveSalvarUmaLocacaoValidaComSucesso() {
        Cliente cliente = clienteRepository.save(ClienteCreator.criarCliente());
        Veiculo veiculo = veiculoRepository.save(VeiculoCreator.criarCarroComum());

        Locacao locacao = new Locacao(cliente, veiculo, 3);
        locacao.setPrecoTotalLocacao(veiculo.getValorDiaria() * 3);
        locacao.setDataCriacao(LocalDateTime.now());
        locacao.setDataFinalizacao(locacao.getDataCriacao().plusDays(3));

        Locacao salvo = locacaoRepository.save(locacao);
        Optional<Locacao> encontrado = locacaoRepository.findById(salvo.getId());

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertTrue(encontrado.isPresent());
        assertEquals(cliente.getId(), encontrado.get().getCliente().getId());
        assertEquals(veiculo.getId(), encontrado.get().getVeiculo().getId());
        assertEquals(locacao.getDiasDeLocacao(), encontrado.get().getDiasDeLocacao());

        TestLocacaoPrinter.printLocacao("Locação salva e encontrada", encontrado.get());
    }

    @Test
    void deveLancarExcecaoQuandoConstraintForViolada() {
        Cliente cliente = clienteRepository.save(ClienteCreator.criarCliente());
        Veiculo veiculo = veiculoRepository.save(VeiculoCreator.criarCarroComum());

        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setVeiculo(veiculo);
        locacao.setDataCriacao(LocalDateTime.now());

        DataIntegrityViolationException exception = assertThrows(
                DataIntegrityViolationException.class,
                () -> locacaoRepository.save(locacao)
        );

        assertNotNull(exception);
    }

    @Test
    void deveBuscarTodasAsLocacoesComSucesso() {
        Cliente cliente = clienteRepository.save(ClienteCreator.criarCliente());
        Veiculo veiculo = veiculoRepository.save(VeiculoCreator.criarCarroComum());

        List<Locacao> locacoes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Locacao locacao = new Locacao(cliente, veiculo, i + 1);
            locacao.setPrecoTotalLocacao(veiculo.getValorDiaria() * locacao.getDiasDeLocacao());
            locacao.setDataFinalizacao(locacao.getDataCriacao().plusDays(locacao.getDiasDeLocacao()));
            locacoes.add(locacaoRepository.save(locacao));
        }

        List<Locacao> todas = locacaoRepository.findAll();

        assertNotNull(todas);
        assertEquals(3, todas.size());

        TestLocacaoPrinter.printLocacoes("Locações encontradas", todas);
    }

    @Test
    void deveAtualizarUmaLocacaoValidaComSucesso() {
        Cliente cliente = clienteRepository.save(ClienteCreator.criarCliente());
        Veiculo veiculo = veiculoRepository.save(VeiculoCreator.criarCarroComum());

        Locacao locacaoOriginal = new Locacao(cliente, veiculo, 3);
        locacaoOriginal.setPrecoTotalLocacao(veiculo.getValorDiaria() * 3);
        locacaoOriginal.setDataFinalizacao(locacaoOriginal.getDataCriacao().plusDays(3));
        locacaoOriginal = locacaoRepository.save(locacaoOriginal);

        locacaoOriginal.setDiasDeLocacao(10);
        locacaoOriginal.setPrecoTotalLocacao(veiculo.getValorDiaria() * 10);
        locacaoOriginal.setDataFinalizacao(locacaoOriginal.getDataCriacao().plusDays(10));
        Locacao locacaoAtualizada = locacaoRepository.save(locacaoOriginal);

        Optional<Locacao> locacaoRecuperada = locacaoRepository.findById(locacaoAtualizada.getId());

        assertTrue(locacaoRecuperada.isPresent());
        assertEquals(10, locacaoRecuperada.get().getDiasDeLocacao());

        TestLocacaoPrinter.printLocacao("Locação atualizada", locacaoRecuperada.get());
    }

    @Test
    void deveDeletarUmaLocacaoComSucesso() {
        Cliente cliente = clienteRepository.save(ClienteCreator.criarCliente());
        Veiculo veiculo = veiculoRepository.save(VeiculoCreator.criarCarroComum());

        Locacao locacao = new Locacao(cliente, veiculo, 3);
        locacao.setPrecoTotalLocacao(veiculo.getValorDiaria() * 3);
        locacao.setDataFinalizacao(locacao.getDataCriacao().plusDays(3));
        locacao = locacaoRepository.save(locacao);

        Long id = locacao.getId();

        locacaoRepository.deleteById(id);

        Optional<Locacao> locacaoRecuperada = locacaoRepository.findById(id);
        boolean existe = locacaoRepository.existsById(id);

        assertAll(
                () -> assertTrue(locacaoRecuperada.isEmpty()),
                () -> assertFalse(existe)
        );
    }
}
