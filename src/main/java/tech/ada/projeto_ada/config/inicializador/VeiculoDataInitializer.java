package tech.ada.projeto_ada.config.inicializador;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.Caminhao;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.CarroComum;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.Moto;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo.CarroPremium;
import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_luxo.Suv;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class VeiculoDataInitializer {

    @Bean
    CommandLineRunner initVeiculos(VeiculoRepository repository) {
        return args -> {
            List<Veiculo> veiculos = gerarVeiculos(
                    3,
                    3,
                    4,
                    2,
                    3
            );

            repository.saveAll(veiculos);
        };
    }

    private List<Veiculo> gerarVeiculos(int quantidadeCaminhao, int quantidadeMoto, int quantidadeCarroComum, int quantidadeCarroPremium, int quantidadeSuv) {
        List<Veiculo> veiculosGerados = new ArrayList<>();

        for (int i = 0; i < quantidadeCaminhao; i++) {
            veiculosGerados.add(new Caminhao("Caminhao " + (i + 1), "CAM" + (i + 1), 600, true, TipoVeiculo.COMUM));
        }

        for (int i = 0; i < quantidadeMoto; i++) {
            veiculosGerados.add(new Moto("Moto " + (i + 1), "MOT" + (i + 1), 230, true, TipoVeiculo.COMUM));
        }

        for (int i = 0; i < quantidadeCarroComum; i++) {
            veiculosGerados.add(new CarroComum("Carro comum " + (i + 1), "CAR" + (i + 1), 320, true, TipoVeiculo.COMUM));
        }

        for (int i = 0; i < quantidadeCarroPremium; i++) {
            veiculosGerados.add(new CarroPremium("Carro Premium " + (i + 1), "PREM" + (i + 1), 780, true, TipoVeiculo.LUXO));
        }

        for (int i = 0; i < quantidadeSuv; i++) {
            veiculosGerados.add(new Suv("SUV " + (i + 1), "SUV" + (i + 1), 1030, true, TipoVeiculo.LUXO));
        }

        return veiculosGerados;
    }
}

