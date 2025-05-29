package tech.ada.projeto_ada.poo1.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.usuario.model.Usuario;

import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByUsuario(Usuario usuario);
}
