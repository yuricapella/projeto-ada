package tech.ada.projeto_ada.usuario.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CriarUsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private CriarUsuarioService criarUsuarioService;

    @Test
    public void testCriarUsuario() {
        // Create a test user
        Usuario usuario = new Usuario();
        usuario.setNome("New User");
        usuario.setEmail("new@example.com");
        usuario.setSenha("password");

        // Mock the repository save method
        when(repository.save(usuario)).thenReturn(usuario);

        // Call the service method
        Usuario result = criarUsuarioService.criarUsuario(usuario);

        // Verify the result
        assertEquals(usuario, result);
        verify(repository, times(1)).save(usuario);
    }
}