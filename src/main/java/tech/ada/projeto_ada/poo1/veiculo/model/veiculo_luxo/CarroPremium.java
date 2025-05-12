package tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo;


import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;

public class CarroPremium extends VeiculoLuxo {
    public CarroPremium(String modelo, String placa, double valorDiaria, boolean disponivel, TipoVeiculo tipo) {
        super(modelo, placa, valorDiaria, disponivel, tipo);
    }

    @Override
    public void oferecerServicoDeLuxo() {
        System.out.println("Serviço de Luxo");
    }
}
