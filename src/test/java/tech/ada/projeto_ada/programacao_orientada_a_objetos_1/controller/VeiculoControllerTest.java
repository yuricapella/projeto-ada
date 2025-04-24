package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.TipoVeiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Veiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service.VeiculoService;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VeiculoController.class)
@Import(VeiculoControllerTest.TestConfig.class)
public class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VeiculoService veiculoService;

    static class TestConfig {
        @Bean
        public VeiculoService veiculoService() {
            return mock(VeiculoService.class);
        }
    }

    @Test
    public void testListarTodos() throws Exception {
        List<Veiculo> veiculos = new ArrayList<>();
        when(veiculoService.listarTodos()).thenReturn(veiculos);

        mockMvc.perform(get("/poo1/veiculos"))
                .andExpect(status().isOk())
                .andExpect(view().name("poo1/veiculo/list"))
                .andExpect(model().attribute("veiculos", veiculos));
    }

    @Test
    public void testListarComuns() throws Exception {
        List<Veiculo> veiculosComuns = new ArrayList<>();
        when(veiculoService.listarPorTipo(TipoVeiculo.COMUM)).thenReturn(veiculosComuns);

        mockMvc.perform(get("/poo1/veiculos/comuns"))
                .andExpect(status().isOk())
                .andExpect(view().name("poo1/veiculo/list-tipo"))
                .andExpect(model().attribute("veiculos", veiculosComuns))
                .andExpect(model().attribute("tipoVeiculo", "Comuns"));
    }

    @Test
    public void testListarLuxo() throws Exception {
        List<Veiculo> veiculosLuxo = new ArrayList<>();
        when(veiculoService.listarPorTipo(TipoVeiculo.LUXO)).thenReturn(veiculosLuxo);

        mockMvc.perform(get("/poo1/veiculos/luxo"))
                .andExpect(status().isOk())
                .andExpect(view().name("poo1/veiculo/list-tipo"))
                .andExpect(model().attribute("veiculos", veiculosLuxo))
                .andExpect(model().attribute("tipoVeiculo", "de Luxo"));
    }

    @Test
    public void testListarDisponiveis() throws Exception {
        List<Veiculo> veiculosDisponiveis = new ArrayList<>();
        when(veiculoService.listarDisponiveis()).thenReturn(veiculosDisponiveis);

        mockMvc.perform(get("/poo1/veiculos/disponiveis"))
                .andExpect(status().isOk())
                .andExpect(view().name("poo1/veiculo/list"))
                .andExpect(model().attribute("veiculos", veiculosDisponiveis));
    }
}
