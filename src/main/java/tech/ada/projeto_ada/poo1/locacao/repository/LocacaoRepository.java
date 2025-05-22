package tech.ada.projeto_ada.poo1.locacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    boolean existsByClienteId(Long clienteId);
    boolean existsByVeiculoId(Long veiculoId);
}
