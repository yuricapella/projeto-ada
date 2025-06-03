package tech.ada.projeto_ada.poo1.veiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.usuario.model.Usuario;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByUsuario(Usuario usuario);

    @Query("SELECT COALESCE(MAX(c.id), 0) FROM Cliente c")
    Long findMaxId();
}
