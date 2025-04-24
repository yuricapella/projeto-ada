package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.TipoVeiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByTipo(TipoVeiculo tipo);
    List<Veiculo> findByDisponivel(boolean disponivel);
    List<Veiculo> findByTipoAndDisponivel(TipoVeiculo tipo, boolean disponivel);
    Optional<Veiculo> findByPlaca(String placa);
}