package tech.ada.projeto_ada.usuario.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.util.TestUsuarioPrinter;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository repository;

    Usuario usuario;

    @BeforeEach
    void setUp(TestInfo testeInfo) {
        TestUsuarioPrinter.printInicioDoTeste(testeInfo.getDisplayName());
        usuario = new Usuario();
        repository.save(new Usuario("Yuri", "yuri@email.com", "12345678"));
        repository.save(new Usuario("Rodolfo", "rodofol@email.com", "12345678"));
        repository.save(new Usuario("Marcos", "marcos@email.com", "12345678"));
    }

    @Test
    void deveSalvarUmUsuarioValidoComSucesso(){
        usuario.setNome("Yuri");
        usuario.setEmail("email@email.com");
        usuario.setSenha("12345678");

        Usuario usuarioSalvo = repository.save(usuario);
        Optional<Usuario> usuarioOptional = repository.findById(usuarioSalvo.getId());

        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());
        assertFalse(usuarioOptional.isEmpty());
        assertEquals(usuario.getNome(), usuarioOptional.get().getNome());
        assertEquals(usuario.getEmail(), usuarioOptional.get().getEmail());
        assertEquals(usuario.getSenha(), usuarioOptional.get().getSenha());

        TestUsuarioPrinter.printUsuarioEncontrado(usuarioOptional.get());
    }

    //testando a anotação do model usuario
    @Test
    void deveLancarExcecaoQuandoConstraintForViolada(){
        usuario.setNome(null);

        DataIntegrityViolationException exception = Assertions.assertThrows(DataIntegrityViolationException.class, () -> repository.save(usuario));

        Assertions.assertNotNull(exception);
    }

    @Test
    void deveBuscarTodosOsUsuariosComSucesso(){
        List<Usuario> usuarios = repository.findAll();

        assertNotNull(usuarios);
        assertEquals(3,usuarios.size());

        TestUsuarioPrinter.printUsuarios("Usuarios encontrados", usuarios);
    }

    @Test
    void deveAtualizarUmUsuarioValidoComSucesso(){
        Usuario usuarioQueSeraAtualizado = new Usuario("Igor", "Igor@email.com", "12345678");

        Usuario usuarioSalvo = repository.save(usuarioQueSeraAtualizado);
        usuarioSalvo.setNome("Igor atualizado");

        Usuario usuarioAtualizado = repository.save(usuarioSalvo);
        Optional<Usuario> usuarioAtualizadoRecuperado = repository.findById(usuarioAtualizado.getId());

        assertFalse(usuarioAtualizadoRecuperado.isEmpty());
        assertEquals(usuarioSalvo.getId(), usuarioAtualizado.getId());
        assertEquals(usuarioSalvo.getNome(), usuarioAtualizadoRecuperado.get().getNome());

        TestUsuarioPrinter.printUsuarioEncontrado(usuarioAtualizadoRecuperado.get());
    }

    @Test
    void deveDeletarUmUsuarioComSucesso(){
        Usuario usuarioSalvo = repository.save(new Usuario("Yuri","yuri@yuri.com","12345678"));

        repository.deleteById(usuarioSalvo.getId());

        Optional<Usuario> usuarioFindById = repository.findById(usuarioSalvo.getId());
        boolean usuarioExistsById = repository.existsById(usuarioSalvo.getId());

        assertAll(
                () -> assertTrue(usuarioFindById.isEmpty()),
                () -> assertFalse(usuarioExistsById)
        );
    }

    @Test
    void deveBuscarUmUsuarioPeloEmailComSucesso(){
        Optional<Usuario> usuarioOptional = repository.findByEmail("yuri@email.com");

        assertTrue(usuarioOptional.isPresent());

        TestUsuarioPrinter.printUsuarioEncontrado(usuarioOptional.get());
    }


    @Test
    void deveBuscarUmUsuarioPeloEmailIgnoreCaseComSucesso(){
        Optional<Usuario> usuarioOptional = repository.findByEmailIgnoreCase("YuRI@EmAiL.CoM");

        assertTrue(usuarioOptional.isPresent());

        TestUsuarioPrinter.printUsuarioEncontrado(usuarioOptional.get());
    }

    @Test
    void deveBuscarUmUsuarioPeloNomeComSucesso(){
        boolean usuarioEncontrado = repository.existsByEmail("yuri@email.com");

        assertTrue(usuarioEncontrado);
    }

}