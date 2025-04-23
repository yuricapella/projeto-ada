package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Cliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
@Import(ClienteControllerTest.TestConfig.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteService clienteService;

    static class TestConfig {
        @Bean
        public ClienteService clienteService() {
            return mock(ClienteService.class);
        }
    }

    @Test
    public void testListarTodos() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        when(clienteService.listarTodos()).thenReturn(clientes);

        mockMvc.perform(get("/poo1/clientes"))
                .andExpect(status().isOk())
                .andExpect(view().name("poo1/cliente/list"))
                .andExpect(model().attribute("clientes", clientes));
    }
}