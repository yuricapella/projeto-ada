package tech.ada.projeto_ada.poo2.consumidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.poo2.consumidor.model.Consumidor;

import java.util.List;

public interface ConsumidorRepository extends JpaRepository<Consumidor, Long> {
    List<Consumidor> findConsumidorByAtivo(boolean ativo);
}
