package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Cliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Locacao;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Veiculo;

import java.util.List;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    List<Locacao> findByCliente(Cliente cliente);
    List<Locacao> findByVeiculo(Veiculo veiculo);
}