package tech.ada.projeto_ada.poo1.veiculo.util;

public enum TipoClasseVeiculo {
    MOTO(tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.Moto.class),
    CARRO_COMUM(tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.CarroComum.class),
    CAMINHAO(tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.Caminhao.class),
    CARRO_PREMIUM(tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo.CarroPremium.class),
    SUV(tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo.Suv.class);

    private final Class<?> classe;

    TipoClasseVeiculo(Class<?> classe) {
        this.classe = classe;
    }

    public Class<?> getClasse() {
        return classe;
    }
}
