package tech.ada.projeto_ada.poo1.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
