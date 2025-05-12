package tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum;


import jakarta.persistence.Entity;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;

@Entity
public class Moto extends Veiculo {

    public Moto() {
    }

    public Moto(String modelo, String placa, double valorDiaria, boolean disponivel, TipoVeiculo tipo) {
        super(modelo, placa, valorDiaria, disponivel, tipo);
    }
}
