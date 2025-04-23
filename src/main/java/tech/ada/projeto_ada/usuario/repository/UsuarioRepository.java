package tech.ada.projeto_ada.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.usuario.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

}
