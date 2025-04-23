package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Cliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.TipoCliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByTipoCliente(TipoCliente tipoCliente);
}