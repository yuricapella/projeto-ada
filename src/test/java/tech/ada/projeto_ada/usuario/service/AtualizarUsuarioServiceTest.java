package tech.ada.projeto_ada.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.ada.projeto_ada.usuario.dto.CriarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.exception.UsuarioNaoEncontradoException;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;
import tech.ada.projeto_ada.usuario.util.TestUsuarioPrinter;

import static org.junit.jupiter.api.Assertions.*;

class AtualizarUsuarioServiceTest {
    BuscarUsuarioService buscarService;
    UsuarioRepository repository;
    PasswordEncoder passwordEncoder;
    AtualizarUsuarioService atualizarService;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestUsuarioPrinter.printInicioDoTeste(testInfo.getDisplayName());
        repository = Mockito.mock(UsuarioRepository.class);
        buscarService = Mockito.mock(BuscarUsuarioService.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        atualizarService = new AtualizarUsuarioService(repository, buscarService, passwordEncoder);
    }

    @Test
    void deveEncontrarUsuarioPorIdEAtualizaloComSucesso(){
        Long id = 1L;
        Usuario usuarioExistente = new Usuario("Yuri","yuri@yuri.com","12345678");
        usuarioExistente.setId(id);
        String nomeAntigo = usuarioExistente.getNome();

        CriarUsuarioRequestDTO usuarioDTO = new CriarUsuarioRequestDTO("Yuri Atualizado","yuri@yuri.com","12345678");
        String senhaOriginal = usuarioDTO.getSenha();
        String senhaCriptografada = "senha_criptografada";
        Usuario usuarioDTOComSenhaCriptografada = new Usuario("Yuri","yuri@yuri.com",senhaCriptografada);

        Mockito.when(buscarService.buscarUsuarioPorId(id)).thenReturn(usuarioExistente);
        Mockito.when(passwordEncoder.encode(senhaOriginal)).thenReturn(senhaCriptografada);
        Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuarioDTOComSenhaCriptografada);

        atualizarService.atualizarUsuario(usuarioDTO,id);

        assertNotEquals(nomeAntigo, usuarioExistente.getNome());
        assertEquals("Yuri Atualizado", usuarioExistente.getNome());
        assertEquals(senhaCriptografada, usuarioExistente.getSenha());

        Mockito.verify(buscarService, Mockito.times(1)).buscarUsuarioPorId(id);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(senhaOriginal);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Usuario.class));

        InOrder inOrder = Mockito.inOrder(buscarService,passwordEncoder,repository);
        inOrder.verify(buscarService, Mockito.times(1)).buscarUsuarioPorId(id);
        inOrder.verify(passwordEncoder, Mockito.times(1)).encode(senhaOriginal);
        inOrder.verify(repository, Mockito.times(1)).save(Mockito.any(Usuario.class));

        TestUsuarioPrinter.printUsuarioAtualizado(usuarioExistente);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForEncontrado(){
        Long id = 99L;
        CriarUsuarioRequestDTO usuarioDTO = new CriarUsuarioRequestDTO("Teste", "teste@email.com", "senha");

        Mockito.when(buscarService.buscarUsuarioPorId(id))
                .thenThrow(new UsuarioNaoEncontradoException(id));

        UsuarioNaoEncontradoException exception = assertThrows(UsuarioNaoEncontradoException.class,
                () -> atualizarService.atualizarUsuario(usuarioDTO, id));

        assertEquals("Usuário com id " + id + " não encontrado.", exception.getMessage());
        Mockito.verify(buscarService, Mockito.times(1)).buscarUsuarioPorId(id);

        TestUsuarioPrinter.printMensagemDeErro(exception.getMessage());
    }
}