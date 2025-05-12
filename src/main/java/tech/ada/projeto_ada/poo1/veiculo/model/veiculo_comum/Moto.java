package tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum;


import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;

public class Moto extends Veiculo {
    public Moto(String modelo, String placa, double valorDiaria, boolean disponivel, TipoVeiculo tipo) {
        super(modelo, placa, valorDiaria, disponivel, tipo);
    }
}
