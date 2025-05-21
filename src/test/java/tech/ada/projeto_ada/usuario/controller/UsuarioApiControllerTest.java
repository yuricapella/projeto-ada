package tech.ada.projeto_ada.usuario.controller;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ada.projeto_ada.config.ControllerAdviceRest;
import tech.ada.projeto_ada.usuario.dto.AtualizarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.dto.CriarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.dto.UsuarioResponseDTO;
import tech.ada.projeto_ada.usuario.exception.UsuarioControllerAdviceRest;
import tech.ada.projeto_ada.usuario.exception.UsuarioNaoEncontradoException;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.service.AtualizarUsuarioService;
import tech.ada.projeto_ada.usuario.service.BuscarUsuarioService;
import tech.ada.projeto_ada.usuario.service.CriarUsuarioService;
import tech.ada.projeto_ada.usuario.service.DeletarUsuarioService;
import tech.ada.projeto_ada.util.JsonUtil;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UsuarioApiControllerTest {
    @InjectMocks
    UsuarioApiController controller;
    @Mock
    BuscarUsuarioService buscarService;
    @Mock
    CriarUsuarioService criarService;
    @Mock
    AtualizarUsuarioService atualizarService;
    @Mock
    DeletarUsuarioService deletarService;

    MockMvc mockMvc;

    final String PATH = "/api/usuarios";
    final String PATH_COM_ID = "/api/usuarios/{id}";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdviceRest(), new UsuarioControllerAdviceRest())
                .build();
    }

    @Test
    void deveBuscarTodosOsUsuariosComSucesso() throws Exception {
        UsuarioResponseDTO usuario = new UsuarioResponseDTO("Yuri", "yuri@yuri.com");
        UsuarioResponseDTO usuario2 = new UsuarioResponseDTO("Yuri2", "yuri2@yuri.com");
        List<UsuarioResponseDTO> usuariosRetornados = List.of(usuario, usuario2);

        Mockito.when(buscarService.buscarTodosUsuarios()).thenReturn(usuariosRetornados);

        mockMvc.perform(get(PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().json(JsonUtil.asJsonString(usuariosRetornados)))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(buscarService, Mockito.times(1)).buscarTodosUsuarios();
    }

    @Test
    void deveBuscarUmUsuarioPorIdComSucesso() throws Exception {
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("yuri");

        Mockito.when(buscarService.buscarUsuarioPorId(id)).thenReturn(usuario);

        mockMvc.perform(get(PATH_COM_ID,id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().json(JsonUtil.asJsonString(usuario)))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(buscarService, Mockito.times(1)).buscarUsuarioPorId(id);
    }


    @Test
    void deveRetornarNotFoundQuandoUsuarioNaoExistir() throws Exception {
        Long id = 999L;
        Mockito.when(buscarService.buscarUsuarioPorId(id))
                .thenThrow(new UsuarioNaoEncontradoException("Usuário com id " + id + " não encontrado."));

        mockMvc.perform(get(PATH_COM_ID, id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());

        Mockito.verify(buscarService, Mockito.times(1)).buscarUsuarioPorId(id);
    }

    @Test
    void deveCriarUsuarioComSucesso() throws Exception {
        CriarUsuarioRequestDTO usuarioRequestDTO = new CriarUsuarioRequestDTO("yuri","email@email.com","12345678");
        Usuario usuarioCriado = new Usuario("yuri","email@email.com","12345678");

        Mockito.when(criarService.criarUsuario(Mockito.any(Usuario.class)))
                .thenReturn(usuarioCriado);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.asJsonString(usuarioRequestDTO)))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().json(JsonUtil.asJsonString(usuarioCriado)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deveRetornarBadRequestQuandoCriarUsuarioInvalido() throws Exception {
        CriarUsuarioRequestDTO usuarioRequestDTO = new CriarUsuarioRequestDTO("yuri","email@email.com","12345678");
        usuarioRequestDTO.setNome("yu");
        usuarioRequestDTO.setEmail("sdasdasd");
        usuarioRequestDTO.setSenha("12345678");

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.asJsonString(usuarioRequestDTO)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deveAtualizarUsuarioPorIdComSucesso() throws Exception {
        Long id = 1L;

        AtualizarUsuarioRequestDTO usuarioDTO = new AtualizarUsuarioRequestDTO(
                "Yuri Atualizado", "yuri@yuri.com", "12345678"
        );

        mockMvc.perform(put(PATH_COM_ID, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(usuarioDTO)))
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(atualizarService, Mockito.times(1))
                .atualizarUsuario(Mockito.argThat(dto ->
                        dto.getNome().equals("Yuri Atualizado") &&
                                dto.getEmail().equals("yuri@yuri.com") &&
                                dto.getSenha().equals("12345678")
                ), Mockito.eq(id));
    }

    @Test
    void deveDeletarUmUsuarioComSucesso() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete(PATH_COM_ID,id))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(deletarService, Mockito.times(1)).deletarUsuarioPorId(id);
    }
}