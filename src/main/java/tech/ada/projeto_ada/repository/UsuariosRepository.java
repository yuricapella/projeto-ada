package tech.ada.projeto_ada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

}
