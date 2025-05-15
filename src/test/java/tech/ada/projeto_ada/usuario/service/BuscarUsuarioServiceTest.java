package tech.ada.projeto_ada.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import tech.ada.projeto_ada.usuario.dto.UsuarioResponseDTO;
import tech.ada.projeto_ada.usuario.exception.UsuarioNaoEncontradoException;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarUsuarioServiceTest {
    BuscarUsuarioService service;
    UsuarioRepository repository;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(UsuarioRepository.class);
        service = new BuscarUsuarioService(repository);
    }

    @BeforeEach
    void logInicio(TestInfo testInfo) {
        System.out.println("==> Iniciando teste: " + testInfo.getDisplayName());
    }

    @Test
    void deveRetornarUsuarioBuscardoPorIdComSucesso(){
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("Yuri");

        Mockito.when(repository.findById(id)).thenReturn(java.util.Optional.of(usuario));

        Usuario usuarioRetornado = service.buscarUsuarioPorId(id);

        assertNotNull(usuarioRetornado);
        assertEquals(id, usuarioRetornado.getId());
        assertEquals("Yuri", usuarioRetornado.getNome());

        System.out.printf("Usuario encontrado: %s, com id %d\n",usuarioRetornado.getNome(), usuarioRetornado.getId());
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
        verify(repository, times(1)).findById(id);

        System.out.println("Erro lançado: " + exception.getMessage());
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
        verify(repository, times(1)).findAll();

        usuariosDTORetornados.stream()
                .map(UsuarioResponseDTO::getNome)
                .forEach(System.out::println);
    }

}