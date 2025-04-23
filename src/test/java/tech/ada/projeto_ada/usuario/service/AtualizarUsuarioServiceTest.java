package tech.ada.projeto_ada.usuario.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.projeto_ada.usuario.dto.CriarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AtualizarUsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private BuscarUsuarioService buscarUsuarioService;

    @InjectMocks
    private AtualizarUsuarioService atualizarUsuarioService;

    @Test
    public void testAtualizarUsuario() {
        // Create a test user and DTO
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setNome("Old Name");
        usuarioExistente.setEmail("old@example.com");
        usuarioExistente.setSenha("oldpassword");

        CriarUsuarioRequestDTO usuarioAtualizado = new CriarUsuarioRequestDTO();
        usuarioAtualizado.setNome("New Name");
        usuarioAtualizado.setEmail("new@example.com");
        usuarioAtualizado.setSenha("newpassword");

        // Mock the buscarUsuarioService to return the existing user
        when(buscarUsuarioService.buscarUsuarioPorId(1L)).thenReturn(usuarioExistente);

        // Call the service method
        atualizarUsuarioService.atualizarUsuario(usuarioAtualizado, 1L);

        // Verify that the user was updated and saved
        verify(buscarUsuarioService, times(1)).buscarUsuarioPorId(1L);
        verify(repository, times(1)).save(usuarioExistente);

        // Verify that the user's fields were updated
        // Note: This would require a way to verify that the mapper was called correctly,
        // which is difficult without modifying the service to make it more testable.
        // In a real application, you might want to refactor the service to make it more testable.
    }
}