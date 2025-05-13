package tech.ada.projeto_ada.poo1.veiculo.dto.mapper;

import tech.ada.projeto_ada.poo1.veiculo.dto.CriarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoClasseVeiculo;

public class CriarVeiculoRequestMapper {

    public static Veiculo toEntity(CriarVeiculoRequestDTO dto) {
        try {
            Class<?> classeVeiculo = TipoClasseVeiculo.valueOf(dto.getTipoClasse().name()).getClasse();

            var constructor = classeVeiculo.getConstructor(String.class, String.class, double.class, boolean.class, tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo.class);
            return (Veiculo) constructor.newInstance(
                    dto.getModelo(),
                    dto.getPlaca(),
                    dto.getValorDiaria(),
                    dto.getDisponivel(),
                    dto.getTipo()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar instância do veículo: " + e.getMessage(), e);
        }
    }
}
