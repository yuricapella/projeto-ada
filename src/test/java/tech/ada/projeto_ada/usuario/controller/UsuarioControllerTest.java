package tech.ada.projeto_ada.usuario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.ada.projeto_ada.usuario.dto.CriarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.dto.UsuarioResponseDTO;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.service.AtualizarUsuarioService;
import tech.ada.projeto_ada.usuario.service.BuscarUsuarioService;
import tech.ada.projeto_ada.usuario.service.CriarUsuarioService;
import tech.ada.projeto_ada.usuario.service.DeletarUsuarioService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioApiController.class)
@Import(UsuarioControllerTest.TestConfig.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private CriarUsuarioService criarUsuarioService;

    @Autowired
    private AtualizarUsuarioService atualizarUsuarioService;

    @Autowired
    private DeletarUsuarioService deletarUsuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    static class TestConfig {
        @Bean
        public BuscarUsuarioService buscarUsuarioService() {
            return mock(BuscarUsuarioService.class);
        }

        @Bean
        public CriarUsuarioService criarUsuarioService() {
            return mock(CriarUsuarioService.class);
        }

        @Bean
        public AtualizarUsuarioService atualizarUsuarioService() {
            return mock(AtualizarUsuarioService.class);
        }

        @Bean
        public DeletarUsuarioService deletarUsuarioService() {
            return mock(DeletarUsuarioService.class);
        }

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }

    @Test
    public void testBuscarTodosUsuarios() throws Exception {
        List<UsuarioResponseDTO> usuarios = new ArrayList<>();
        when(buscarUsuarioService.buscarTodosUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testBuscarUsuarioPorId() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Test User");
        usuario.setEmail("test@example.com");

        when(buscarUsuarioService.buscarUsuarioPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Test User"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testCriarUsuario() throws Exception {
        CriarUsuarioRequestDTO requestDTO = new CriarUsuarioRequestDTO();
        requestDTO.setNome("New User");
        requestDTO.setEmail("new@example.com");
        requestDTO.setSenha("password");

        Usuario createdUsuario = new Usuario();
        createdUsuario.setId(1L);
        createdUsuario.setNome("New User");
        createdUsuario.setEmail("new@example.com");

        when(criarUsuarioService.criarUsuario(any(Usuario.class))).thenReturn(createdUsuario);

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("New User"))
                .andExpect(jsonPath("$.email").value("new@example.com"));
    }

    @Test
    public void testAtualizarUsuario() throws Exception {
        CriarUsuarioRequestDTO requestDTO = new CriarUsuarioRequestDTO();
        requestDTO.setNome("Updated User");
        requestDTO.setEmail("updated@example.com");
        requestDTO.setSenha("newpassword");

        doNothing().when(atualizarUsuarioService).atualizarUsuario(any(CriarUsuarioRequestDTO.class), anyLong());

        mockMvc.perform(put("/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletarUsuario() throws Exception {
        doNothing().when(deletarUsuarioService).deletarUsuarioPorId(anyLong());

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }
}