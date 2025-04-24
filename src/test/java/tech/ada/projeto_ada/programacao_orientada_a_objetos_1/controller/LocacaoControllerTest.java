package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Cliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Locacao;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.TipoVeiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Veiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service.ClienteService;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service.LocacaoService;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service.VeiculoService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LocacaoController.class)
@Import(LocacaoControllerTest.TestConfig.class)
public class LocacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocacaoService locacaoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoService veiculoService;

    static class TestConfig {
        @Bean
        public LocacaoService locacaoService() {
            return mock(LocacaoService.class);
        }

        @Bean
        public ClienteService clienteService() {
            return mock(ClienteService.class);
        }

        @Bean
        public VeiculoService veiculoService() {
            return mock(VeiculoService.class);
        }
    }

    @Test
    public void testListarTodas() throws Exception {
        List<Locacao> locacoes = new ArrayList<>();
        when(locacaoService.listarTodas()).thenReturn(locacoes);

        mockMvc.perform(get("/poo1/locacoes"))
                .andExpect(status().isOk())
                .andExpect(view().name("poo1/locacao/list"))
                .andExpect(model().attribute("locacoes", locacoes));
    }

    @Test
    public void testFormAlugarComuns() throws Exception {
        List<Veiculo> veiculos = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        when(veiculoService.listarDisponiveisPorTipo(TipoVeiculo.COMUM)).thenReturn(veiculos);
        when(clienteService.listarTodos()).thenReturn(clientes);

        mockMvc.perform(get("/poo1/locacoes/alugar/comuns"))
                .andExpect(status().isOk())
                .andExpect(view().name("poo1/locacao/create"))
                .andExpect(model().attribute("veiculos", veiculos))
                .andExpect(model().attribute("clientes", clientes))
                .andExpect(model().attribute("tipoVeiculo", "Comuns"));
    }

    @Test
    public void testFormAlugarLuxo() throws Exception {
        List<Veiculo> veiculos = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        when(veiculoService.listarDisponiveisPorTipo(TipoVeiculo.LUXO)).thenReturn(veiculos);
        when(clienteService.listarTodos()).thenReturn(clientes);

        mockMvc.perform(get("/poo1/locacoes/alugar/luxo"))
                .andExpect(status().isOk())
                .andExpect(view().name("poo1/locacao/create"))
                .andExpect(model().attribute("veiculos", veiculos))
                .andExpect(model().attribute("clientes", clientes))
                .andExpect(model().attribute("tipoVeiculo", "de Luxo"));
    }

    @Test
    public void testAlugar() throws Exception {
        Cliente cliente = mock(Cliente.class);
        Veiculo veiculo = mock(Veiculo.class);
        Locacao locacao = mock(Locacao.class);

        when(clienteService.buscarPorId(anyLong())).thenReturn(cliente);
        when(veiculoService.buscarPorPlaca(anyString())).thenReturn(veiculo);
        when(locacaoService.alugarVeiculo(any(Cliente.class), any(Veiculo.class), eq(3))).thenReturn(locacao);

        mockMvc.perform(post("/poo1/locacoes/alugar")
                .param("clienteId", "1")
                .param("veiculoPlaca", "ABC1234")
                .param("periodoLocacao", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/poo1/locacoes"));
    }
}