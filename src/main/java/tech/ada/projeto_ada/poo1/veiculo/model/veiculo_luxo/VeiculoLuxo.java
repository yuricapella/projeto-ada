package tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo;

import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;

public abstract class VeiculoLuxo extends Veiculo implements ServicoLuxo {

    public VeiculoLuxo(String modelo, String placa, double valorDiaria, boolean disponivel, TipoVeiculo tipo) {
        super(modelo, placa, valorDiaria, disponivel, tipo);
    }

    public void oferecerServicoDeLuxo(Boolean ativar) {
        if (ativar) {
            System.out.println("Serviço de luxo ativado: " + getModelo());
        } else {
            System.out.println("Serviço de luxo desativado: " + getModelo());
        }
    }
}