package tech.ada.projeto_ada.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CriarUsuarioServiceTest {
    UsuarioRepository repository;
    PasswordEncoder passwordEncoder;
    CriarUsuarioService service;

    @BeforeEach
    void setup(){
        repository = Mockito.mock(UsuarioRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        service = new CriarUsuarioService(repository, passwordEncoder);
    }

    @BeforeEach
    void logInicio(TestInfo testInfo) {
        System.out.println("==> Iniciando teste: " + testInfo.getDisplayName());
    }

    @Test
    void deveCriarUmUsuarioComSucesso() {
        Usuario usuario = new Usuario("Yuri","yuri@yuri.com","12345678");
        Long id = 1L;

        String senhaOriginal = usuario.getSenha();
        String senhaCriptografada = "senha_criptografada";

        Mockito.when(passwordEncoder.encode(usuario.getSenha())).thenReturn(senhaCriptografada);

        Usuario usuarioComSenhaCriptografada = new Usuario("Yuri","yuri@yuri.com",senhaCriptografada);
        usuarioComSenhaCriptografada.setId(id);
        usuarioComSenhaCriptografada.setDataCriacao(LocalDateTime.now());

        Mockito.when(repository.save(Mockito.any(Usuario.class)))
                .thenReturn(usuarioComSenhaCriptografada);


        Usuario usuarioCriado = service.criarUsuario(usuario);

        assertNotNull(usuarioCriado);
        assertNotNull(usuarioCriado.getDataCriacao());
        assertEquals(id,usuarioCriado.getId());
        assertEquals("Yuri", usuarioCriado.getNome());
        assertEquals("yuri@yuri.com", usuarioCriado.getEmail());
        assertEquals(senhaCriptografada, usuarioCriado.getSenha());
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Usuario.class));
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(senhaOriginal);
        InOrder inOrder = Mockito.inOrder(passwordEncoder,repository);
        inOrder.verify(passwordEncoder, Mockito.times(1)).encode(senhaOriginal);
        inOrder.verify(repository, Mockito.times(1)).save(Mockito.any(Usuario.class));

        printUsuario(usuarioCriado);
    }

    @Test
    void deveLancarExcecaoAoCriarUsuarioComEmailJaCadastrado(){
        Usuario usuario = new Usuario("Yuri","email@email.com","12345678");
        Usuario usuarioInvalido = new Usuario("Igor","email@email.com","987654321");

        Mockito.when(repository.existsByEmail(usuario.getEmail())).thenReturn(true);

        DataIntegrityViolationException exception = assertThrows(
                DataIntegrityViolationException.class, () -> service.criarUsuario(usuarioInvalido)
        );

        assertNotNull(exception);
        assertEquals("Email já cadastrado", exception.getMessage());
        Mockito.verify(repository, Mockito.times(1)).existsByEmail(usuario.getEmail());

        System.out.println("Erro lançado: " + exception.getMessage());
    }


    private void printUsuario(Usuario usuario){
        System.out.printf(
                "Usuário criado => ID: %d | Nome: %s | Email: %s | Senha: %s | Criado em: %s%n" +
                        "Atualizado em: %s%n",
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getDataCriacao(),
                usuario.getDataAtualizacao()
        );
    }
}