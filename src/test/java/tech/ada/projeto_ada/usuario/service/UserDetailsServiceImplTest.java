package tech.ada.projeto_ada.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;
import tech.ada.projeto_ada.usuario.util.TestUsuarioPrinter;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsServiceImplTest {
    UsuarioRepository repository;
    UserDetailsServiceImpl service;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestUsuarioPrinter.printInicioDoTeste(testInfo.getDisplayName());
        repository = Mockito.mock(UsuarioRepository.class);
        service = new UserDetailsServiceImpl(repository);
    }


    @Test
    void deveBuscarUsuarioPorEmailERetornarUserDetailsComSucesso() {
        String email = "teste@teste.com";
        Usuario usuario = new Usuario("Teste",email,"senha123");

        Mockito.when(repository.findByEmailIgnoreCase(email)).thenReturn(Optional.of(usuario));

        UserDetails userDetails = service.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals("senha123", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("USER")));

        Mockito.verify(repository, Mockito.times(1)).findByEmailIgnoreCase(usuario.getEmail());

        TestUsuarioPrinter.printUserDetails(userDetails);
    }

    @Test
    void deveLancarExcecaoQuandoEmailForInvalido() {
        String email = "teste@teste.com";

        Mockito.when(repository.findByEmailIgnoreCase(email)).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class, () -> service.loadUserByUsername(email));

        assertNotNull(exception);
        assertEquals("Usuário não encontrado com o email: " + email, exception.getMessage());

        Mockito.verify(repository, Mockito.times(1)).findByEmailIgnoreCase(email);

        TestUsuarioPrinter.printMensagemDeErro(exception.getMessage());
    }
}