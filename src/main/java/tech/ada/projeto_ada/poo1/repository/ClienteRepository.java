package tech.ada.projeto_ada.poo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.poo1.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
