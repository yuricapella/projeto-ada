package tech.ada.projeto_ada.logica_programacao.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ada.projeto_ada.config.ControllerAdviceRest;
import tech.ada.projeto_ada.logica_programacao.exception.SalarioInvalidoException;
import tech.ada.projeto_ada.logica_programacao.exception.SalaryControllerAdviceRest;
import tech.ada.projeto_ada.logica_programacao.model.Salary;
import tech.ada.projeto_ada.logica_programacao.service.SalaryService;
import tech.ada.projeto_ada.usuario.exception.UsuarioControllerAdviceRest;
import tech.ada.projeto_ada.util.JsonUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SalaryApiControllerTest {
    @InjectMocks
    SalaryApiController controller;
    @Mock
    SalaryService service;

    MockMvc mockMvc;

    final String PATH = "/api/logica-programacao/salarios";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdviceRest(), new SalaryControllerAdviceRest())
                .build();
    }

    @Test
    void deveCalcularUmSalarioComDependentesComSucesso() throws Exception {
        Salary salarioEntrada = new Salary(5000.0);

        Salary salarioCalculado = new Salary(5000.0);
        salarioCalculado.setNumeroDependentes(2);
        salarioCalculado.setDescontoInss(509.5968);
        salarioCalculado.setDescontoImpostoDeRenda(262.2552199999999);
        salarioCalculado.setInssPercentual(14.0);
        salarioCalculado.setIrrfPercentual(22.5);
        salarioCalculado.setSalarioLiquido(4228.14798);

        mockMvc.perform(post(PATH)
                        .param("dependentes", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(List.of(salarioEntrada))))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.asJsonString(List.of(salarioCalculado))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deveCalcularUmSalarioSemDependentesComSucesso() throws Exception {
        Salary salarioEntrada = new Salary(5000.0);

        Salary salarioCalculado = new Salary(5000.0);
        salarioCalculado.setNumeroDependentes(0);
        salarioCalculado.setDescontoInss(509.5968);
        salarioCalculado.setDescontoImpostoDeRenda(347.57071999999994);
        salarioCalculado.setInssPercentual(14.0);
        salarioCalculado.setIrrfPercentual(22.5);
        salarioCalculado.setSalarioLiquido(4142.83248);

        mockMvc.perform(post(PATH)
                        .param("dependentes", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(List.of(salarioEntrada))))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.asJsonString(List.of(salarioCalculado))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deveLancarBadRequestQuandoSalarioInvalido() throws Exception {
        Salary salario = new Salary(-100.0);

        mockMvc.perform(post(PATH)
                        .param("dependentes", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(List.of(salario))))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.mensagem")
                        .value("Salario: " + salario.getSalarioBruto() + " incorreto, deve ser maior que 0"))
                .andDo(MockMvcResultHandlers.print());
    }
}