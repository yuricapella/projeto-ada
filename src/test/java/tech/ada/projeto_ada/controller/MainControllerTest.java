package tech.ada.projeto_ada.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void deveRedirecionarParaLogin() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andDo(print());
    }

    @Test
    void deveCarregarPaginaDeLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/login"))
                .andDo(print());
    }

    @Test
    void deveCarregarPaginaDeCadastro() throws Exception {
        mockMvc.perform(get("/cadastro"))
                .andExpect(status().isOk())
                .andExpect(view().name("cadastro/cadastro"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void deveCarregarHomeQuandoAutenticado() throws Exception {
        mockMvc.perform(get("/home")
                        .with(request -> {
                            request.setAttribute("_csrf", new org.springframework.security.web.csrf.DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", "dev"));
                            return request;
                        }))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andDo(print());
    }
}