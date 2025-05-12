package tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo;

import jakarta.persistence.Entity;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;

@Entity
public class Suv extends VeiculoLuxo {

    public Suv() {
    }

    public Suv(String modelo, String placa, double valorDiaria, boolean disponivel, TipoVeiculo tipo) {
        super(modelo, placa, valorDiaria, disponivel, tipo);
    }

    @Override
    public void oferecerServicoDeLuxo() {
        System.out.println("Serviço de Luxo");
    }
}
