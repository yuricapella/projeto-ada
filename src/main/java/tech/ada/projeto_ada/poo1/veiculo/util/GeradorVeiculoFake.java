package tech.ada.projeto_ada.poo1.veiculo.util;

import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.Caminhao;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.CarroComum;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.Moto;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo.CarroPremium;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo.Suv;
import tech.ada.projeto_ada.poo1.veiculo.service.BuscarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.CriarVeiculoService;

import java.util.ArrayList;
import java.util.List;

public class GeradorVeiculoFake {

    private final CriarVeiculoService criarVeiculoService;
    private final BuscarVeiculoService buscarVeiculoService;

    public GeradorVeiculoFake(CriarVeiculoService criarVeiculoService, BuscarVeiculoService buscarVeiculoService) {
        this.criarVeiculoService = criarVeiculoService;
        this.buscarVeiculoService = buscarVeiculoService;
    }

    public List<Veiculo> criarVeiculosFakes(int quantidadeCaminhao, int quantidadeMoto, int quantidadeCarroComum, int quantidadeCarroPremium, int quantidadeSuv) {
        List<Veiculo> veiculosCriados = new ArrayList<>();
        Long maiorId = buscarVeiculoService.obterMaiorId();
        if (maiorId == null) {
            maiorId = 0L;
        }

        for (int i = 1; i <= quantidadeCaminhao; i++) {
            int contador = maiorId.intValue() + i;
            Veiculo veiculo = new Caminhao("Caminhao " + contador, "CAM" + contador, 600, true, TipoVeiculo.COMUM);
            veiculosCriados.add(criarVeiculoService.criarVeiculo(veiculo));
        }

        for (int i = 1; i <= quantidadeMoto; i++) {
            int contador = maiorId.intValue() + i;
            Veiculo veiculo = new Moto("Moto " + contador, "MOT" + contador, 230, true, TipoVeiculo.COMUM);
            veiculosCriados.add(criarVeiculoService.criarVeiculo(veiculo));
        }

        for (int i = 1; i <= quantidadeCarroComum; i++) {
            int contador = maiorId.intValue() + i;
            Veiculo veiculo = new CarroComum("Carro comum " + contador, "CAR" + contador, 320, true, TipoVeiculo.COMUM);
            veiculosCriados.add(criarVeiculoService.criarVeiculo(veiculo));
        }

        for (int i = 1; i <= quantidadeCarroPremium; i++) {
            int contador = maiorId.intValue() + i;
            Veiculo veiculo = new CarroPremium("Carro Premium " + contador, "PREM" + contador, 780, true, TipoVeiculo.LUXO);
            veiculosCriados.add(criarVeiculoService.criarVeiculo(veiculo));
        }

        for (int i = 1; i <= quantidadeSuv; i++) {
            int contador = maiorId.intValue() + i;
            Veiculo veiculo = new Suv("SUV " + contador, "SUV" + contador, 1030, true, TipoVeiculo.LUXO);
            veiculosCriados.add(criarVeiculoService.criarVeiculo(veiculo));
        }

        return veiculosCriados;
    }
}
