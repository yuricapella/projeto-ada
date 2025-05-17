package tech.ada.projeto_ada.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.usuario.dto.UsuarioResponseDTO;
import tech.ada.projeto_ada.usuario.exception.UsuarioNaoEncontradoException;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;
import tech.ada.projeto_ada.usuario.util.TestUsuarioPrinter;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarUsuarioServiceTest {
    BuscarUsuarioService service;
    UsuarioRepository repository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestUsuarioPrinter.printInicioDoTeste(testInfo.getDisplayName());
        repository = Mockito.mock(UsuarioRepository.class);
        service = new BuscarUsuarioService(repository);
    }

    @Test
    void deveRetornarUsuarioBuscadoPorIdComSucesso(){
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("Yuri");

        Mockito.when(repository.findById(id)).thenReturn(java.util.Optional.of(usuario));

        Usuario usuarioRetornado = service.buscarUsuarioPorId(id);

        assertNotNull(usuarioRetornado);
        assertEquals(id, usuarioRetornado.getId());
        assertEquals("Yuri", usuarioRetornado.getNome());

        TestUsuarioPrinter.printUsuarioEncontrado(usuarioRetornado);
    }

    @Test
    public void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long id = 3L;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        UsuarioNaoEncontradoException exception = assertThrows(
                UsuarioNaoEncontradoException.class, () -> service.buscarUsuarioPorId(id)
        );

        assertNotNull(exception);
        assertEquals("Usuário com id " + id + " não encontrado.", exception.getMessage());

        Mockito.verify(repository, times(1)).findById(id);

        TestUsuarioPrinter.printMensagemDeErro(exception.getMessage());
    }

    @Test
    void deveRetornarTodosOsUsuariosComSucesso() {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Yuri");
        Usuario usuario2 = new Usuario();
        usuario2.setNome("Ana");

        Mockito.when(repository.findAll()).thenReturn(List.of(usuario1, usuario2));

        List<UsuarioResponseDTO> usuariosDTORetornados = service.buscarTodosUsuarios();

        assertNotNull(usuariosDTORetornados);
        assertEquals(2, usuariosDTORetornados.size());

        Mockito.verify(repository, times(1)).findAll();

        TestUsuarioPrinter.printUsuariosResponse("Usuários encontrados", usuariosDTORetornados);
    }

}