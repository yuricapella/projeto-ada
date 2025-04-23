package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.TipoVeiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Veiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.repository.VeiculoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    private Veiculo veiculoComum;
    private Veiculo veiculoLuxo;

    @BeforeEach
    public void setUp() {
        // Create test vehicles
        veiculoComum = mock(Veiculo.class);
        when(veiculoComum.getTipo()).thenReturn(TipoVeiculo.COMUM);
        when(veiculoComum.getPlaca()).thenReturn("ABC1234");
        when(veiculoComum.isDisponivel()).thenReturn(true);

        veiculoLuxo = mock(Veiculo.class);
        when(veiculoLuxo.getTipo()).thenReturn(TipoVeiculo.LUXO);
        when(veiculoLuxo.getPlaca()).thenReturn("XYZ9876");
        when(veiculoLuxo.isDisponivel()).thenReturn(false);
    }

    @Test
    public void testListarTodos() {
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(veiculoComum);
        veiculos.add(veiculoLuxo);

        when(veiculoRepository.findAll()).thenReturn(veiculos);

        List<Veiculo> result = veiculoService.listarTodos();

        assertEquals(2, result.size());
        verify(veiculoRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculoComum));
        when(veiculoRepository.findById(2L)).thenReturn(Optional.empty());

        Veiculo result = veiculoService.buscarPorId(1L);
        assertEquals(veiculoComum, result);

        assertThrows(RuntimeException.class, () -> veiculoService.buscarPorId(2L));
    }

    @Test
    public void testBuscarPorPlaca() {
        when(veiculoRepository.findByPlaca("ABC1234")).thenReturn(Optional.of(veiculoComum));
        when(veiculoRepository.findByPlaca("NOTFOUND")).thenReturn(Optional.empty());

        Veiculo result = veiculoService.buscarPorPlaca("ABC1234");
        assertEquals(veiculoComum, result);

        assertThrows(RuntimeException.class, () -> veiculoService.buscarPorPlaca("NOTFOUND"));
    }

    @Test
    public void testListarPorTipo() {
        List<Veiculo> veiculosComuns = new ArrayList<>();
        veiculosComuns.add(veiculoComum);

        when(veiculoRepository.findByTipo(TipoVeiculo.COMUM)).thenReturn(veiculosComuns);

        List<Veiculo> result = veiculoService.listarPorTipo(TipoVeiculo.COMUM);

        assertEquals(1, result.size());
        assertEquals(veiculoComum, result.get(0));
        verify(veiculoRepository, times(1)).findByTipo(TipoVeiculo.COMUM);
    }

    @Test
    public void testListarDisponiveis() {
        List<Veiculo> veiculosDisponiveis = new ArrayList<>();
        veiculosDisponiveis.add(veiculoComum);

        when(veiculoRepository.findByDisponivel(true)).thenReturn(veiculosDisponiveis);

        List<Veiculo> result = veiculoService.listarDisponiveis();

        assertEquals(1, result.size());
        assertEquals(veiculoComum, result.get(0));
        verify(veiculoRepository, times(1)).findByDisponivel(true);
    }

    @Test
    public void testListarDisponiveisPorTipo() {
        List<Veiculo> veiculosComunsDisponiveis = new ArrayList<>();
        veiculosComunsDisponiveis.add(veiculoComum);

        when(veiculoRepository.findByTipoAndDisponivel(TipoVeiculo.COMUM, true)).thenReturn(veiculosComunsDisponiveis);

        List<Veiculo> result = veiculoService.listarDisponiveisPorTipo(TipoVeiculo.COMUM);

        assertEquals(1, result.size());
        assertEquals(veiculoComum, result.get(0));
        verify(veiculoRepository, times(1)).findByTipoAndDisponivel(TipoVeiculo.COMUM, true);
    }

    @Test
    public void testSalvar() {
        when(veiculoRepository.save(veiculoComum)).thenReturn(veiculoComum);

        Veiculo result = veiculoService.salvar(veiculoComum);

        assertEquals(veiculoComum, result);
        verify(veiculoRepository, times(1)).save(veiculoComum);
    }

    @Test
    public void testAtualizarDisponibilidade() {
        when(veiculoRepository.findByPlaca("ABC1234")).thenReturn(Optional.of(veiculoComum));

        veiculoService.atualizarDisponibilidade("ABC1234", false);

        verify(veiculoComum, times(1)).setDisponivel(false);
        verify(veiculoRepository, times(1)).save(veiculoComum);
    }
}