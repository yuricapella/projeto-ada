package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Cliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Locacao;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Veiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.repository.LocacaoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocacaoServiceTest {

    @Mock
    private LocacaoRepository locacaoRepository;

    @Mock
    private VeiculoService veiculoService;

    @InjectMocks
    private LocacaoService locacaoService;

    private Cliente cliente;
    private Veiculo veiculoDisponivel;
    private Veiculo veiculoIndisponivel;
    private Locacao locacao;

    @BeforeEach
    public void setUp() {
        // Create test objects
        cliente = mock(Cliente.class);
        
        veiculoDisponivel = mock(Veiculo.class);
        when(veiculoDisponivel.isDisponivel()).thenReturn(true);
        when(veiculoDisponivel.getPlaca()).thenReturn("ABC1234");
        
        veiculoIndisponivel = mock(Veiculo.class);
        when(veiculoIndisponivel.isDisponivel()).thenReturn(false);
        when(veiculoIndisponivel.getPlaca()).thenReturn("XYZ9876");
        
        locacao = mock(Locacao.class);
    }

    @Test
    public void testListarTodas() {
        List<Locacao> locacoes = new ArrayList<>();
        locacoes.add(locacao);

        when(locacaoRepository.findAll()).thenReturn(locacoes);

        List<Locacao> result = locacaoService.listarTodas();

        assertEquals(1, result.size());
        verify(locacaoRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId() {
        when(locacaoRepository.findById(1L)).thenReturn(Optional.of(locacao));
        when(locacaoRepository.findById(2L)).thenReturn(Optional.empty());

        Locacao result = locacaoService.buscarPorId(1L);
        assertEquals(locacao, result);

        assertThrows(RuntimeException.class, () -> locacaoService.buscarPorId(2L));
    }

    @Test
    public void testListarPorCliente() {
        List<Locacao> locacoes = new ArrayList<>();
        locacoes.add(locacao);

        when(locacaoRepository.findByCliente(cliente)).thenReturn(locacoes);

        List<Locacao> result = locacaoService.listarPorCliente(cliente);

        assertEquals(1, result.size());
        assertEquals(locacao, result.get(0));
        verify(locacaoRepository, times(1)).findByCliente(cliente);
    }

    @Test
    public void testListarPorVeiculo() {
        List<Locacao> locacoes = new ArrayList<>();
        locacoes.add(locacao);

        when(locacaoRepository.findByVeiculo(veiculoDisponivel)).thenReturn(locacoes);

        List<Locacao> result = locacaoService.listarPorVeiculo(veiculoDisponivel);

        assertEquals(1, result.size());
        assertEquals(locacao, result.get(0));
        verify(locacaoRepository, times(1)).findByVeiculo(veiculoDisponivel);
    }

    @Test
    public void testAlugarVeiculoDisponivel() {
        when(locacaoRepository.save(any(Locacao.class))).thenReturn(locacao);

        Locacao result = locacaoService.alugarVeiculo(cliente, veiculoDisponivel, 3);

        assertEquals(locacao, result);
        verify(veiculoService, times(1)).atualizarDisponibilidade("ABC1234", false);
        verify(locacaoRepository, times(1)).save(any(Locacao.class));
    }

    @Test
    public void testAlugarVeiculoIndisponivel() {
        assertThrows(RuntimeException.class, () -> locacaoService.alugarVeiculo(cliente, veiculoIndisponivel, 3));
        
        verify(veiculoService, never()).atualizarDisponibilidade(anyString(), anyBoolean());
        verify(locacaoRepository, never()).save(any(Locacao.class));
    }
}