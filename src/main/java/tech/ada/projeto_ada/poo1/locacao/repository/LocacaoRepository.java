package tech.ada.projeto_ada.poo1.locacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.usuario.model.Usuario;

import java.util.List;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    boolean existsByClienteId(Long clienteId);
    boolean existsByVeiculoId(Long veiculoId);
    List<Locacao> findByUsuario(Usuario usuario);

}
