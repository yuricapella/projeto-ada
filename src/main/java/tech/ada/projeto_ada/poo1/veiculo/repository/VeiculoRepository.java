package tech.ada.projeto_ada.poo1.veiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
//    Veiculo findByPlaca(String placa);
//
//    Veiculo findByModelo(String modelo);
//
//    Veiculo findByTipoVeiculo(String tipoVeiculo);
}
