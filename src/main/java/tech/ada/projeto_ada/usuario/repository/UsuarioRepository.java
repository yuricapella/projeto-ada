package tech.ada.projeto_ada.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.usuario.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
