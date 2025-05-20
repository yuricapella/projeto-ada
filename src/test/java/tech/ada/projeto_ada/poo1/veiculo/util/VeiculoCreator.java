package tech.ada.projeto_ada.poo1.veiculo.util;

import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.Caminhao;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.CarroComum;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.Moto;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo.CarroPremium;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo.Suv;

import java.util.ArrayList;
import java.util.List;

public class VeiculoCreator {
    public static Veiculo criarCaminhao() {
        return new Caminhao("Caminhao", "CAM123", 600, true, TipoVeiculo.COMUM);
    }

    public static Veiculo criarMoto() {
        return new Moto("Moto", "MOT123", 230, true, TipoVeiculo.COMUM);
    }

    public static Veiculo criarCarroComum() {
        return new CarroComum("Carro Comum", "CAR123", 320, true, TipoVeiculo.COMUM);
    }

    public static Veiculo criarCarroPremium() {
        return new CarroPremium("Carro Premium", "PREM123", 780, true, TipoVeiculo.LUXO);
    }

    public static Veiculo criarSuv() {
        return new Suv("SUV", "SUV123", 1030, true, TipoVeiculo.LUXO);
    }

    public static List<Veiculo> criarVeiculos(int quantidadeCaminhao,
                                              int quantidadeMoto,
                                              int quantidadeCarroComum,
                                              int quantidadeCarroPremium,
                                              int quantidadeSuv) {
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 1; i <= quantidadeCaminhao; i++) {
            veiculos.add(new Caminhao("Caminhao " + i, "CAM" + i, 600, true, TipoVeiculo.COMUM));
        }
        for (int i = 1; i <= quantidadeMoto; i++) {
            veiculos.add(new Moto("Moto " + i, "MOT" + i, 230, true, TipoVeiculo.COMUM));
        }
        for (int i = 1; i <= quantidadeCarroComum; i++) {
            veiculos.add(new CarroComum("Carro Comum " + i, "CAR" + i, 320, true, TipoVeiculo.COMUM));
        }
        for (int i = 1; i <= quantidadeCarroPremium; i++) {
            veiculos.add(new CarroPremium("Carro Premium " + i, "PREM" + i, 780, true, TipoVeiculo.LUXO));
        }
        for (int i = 1; i <= quantidadeSuv; i++) {
            veiculos.add(new Suv("SUV " + i, "SUV" + i, 1030, true, TipoVeiculo.LUXO));
        }

        return veiculos;
    }
}
