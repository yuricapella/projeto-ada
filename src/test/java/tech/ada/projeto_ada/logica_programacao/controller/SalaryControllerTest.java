package tech.ada.projeto_ada.logica_programacao.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SalaryController.class)
public class SalaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowFormEndpoint() throws Exception {
        mockMvc.perform(get("/salario"))
                .andExpect(status().isOk())
                .andExpect(view().name("logica_programacao/form"));
    }

    @Test
    public void testCalculateSalariesEndpoint() throws Exception {
        MvcResult result = mockMvc.perform(post("/calculate")
                .param("salaries", "1000, 2000, 3000"))
                .andExpect(status().isOk())
                .andExpect(view().name("logica_programacao/result"))
                .andExpect(model().attributeExists("salaries"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("1000"));
        assertTrue(content.contains("2000"));
        assertTrue(content.contains("3000"));
    }

    @Test
    public void testCalculateSalariesWithSingleSalary() throws Exception {
        mockMvc.perform(post("/calculate")
                .param("salaries", "1500"))
                .andExpect(status().isOk())
                .andExpect(view().name("logica_programacao/result"))
                .andExpect(model().attributeExists("salaries"));
    }

    @Test
    public void testCalculateSalariesWithSpaceSeparatedValues() throws Exception {
        mockMvc.perform(post("/calculate")
                .param("salaries", "1000 2000 3000"))
                .andExpect(status().isOk())
                .andExpect(view().name("logica_programacao/result"))
                .andExpect(model().attributeExists("salaries"));
    }
}