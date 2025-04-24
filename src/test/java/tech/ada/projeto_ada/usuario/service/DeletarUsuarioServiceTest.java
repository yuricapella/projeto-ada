package tech.ada.projeto_ada.usuario.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletarUsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private BuscarUsuarioService buscarUsuarioService;

    @InjectMocks
    private DeletarUsuarioService deletarUsuarioService;

    @Test
    public void testDeletarUsuarioPorId() {
        // Create a test user
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Test User");
        usuario.setEmail("test@example.com");

        // Mock the buscarUsuarioService to return the user
        when(buscarUsuarioService.buscarUsuarioPorId(1L)).thenReturn(usuario);

        // Call the service method
        deletarUsuarioService.deletarUsuarioPorId(1L);

        // Verify that the user was found and deleted
        verify(buscarUsuarioService, times(1)).buscarUsuarioPorId(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}