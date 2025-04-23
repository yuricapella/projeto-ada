package tech.ada.projeto_ada.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.projeto_ada.exception.UsuarioNaoEncontradoException;
import tech.ada.projeto_ada.usuario.dto.UsuarioResponseDTO;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarUsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private BuscarUsuarioService buscarUsuarioService;

    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    public void setUp() {
        // Create test users
        usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNome("User 1");
        usuario1.setEmail("user1@example.com");

        usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNome("User 2");
        usuario2.setEmail("user2@example.com");
    }

    @Test
    public void testBuscarTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(repository.findAll()).thenReturn(usuarios);

        List<UsuarioResponseDTO> result = buscarUsuarioService.buscarTodosUsuarios();

        assertEquals(2, result.size());
        assertEquals("User 1", result.get(0).getNome());
        assertEquals("user1@example.com", result.get(0).getEmail());
        assertEquals("User 2", result.get(1).getNome());
        assertEquals("user2@example.com", result.get(1).getEmail());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testBuscarUsuarioPorIdExistente() {
        when(repository.findById(1L)).thenReturn(Optional.of(usuario1));

        Usuario result = buscarUsuarioService.buscarUsuarioPorId(1L);

        assertEquals(usuario1, result);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void testBuscarUsuarioPorIdInexistente() {
        when(repository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> buscarUsuarioService.buscarUsuarioPorId(3L));
        verify(repository, times(1)).findById(3L);
    }
}