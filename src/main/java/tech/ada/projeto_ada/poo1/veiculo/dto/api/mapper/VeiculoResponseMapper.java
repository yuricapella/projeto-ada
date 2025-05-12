package tech.ada.projeto_ada.poo1.veiculo.dto.api.mapper;

import tech.ada.projeto_ada.poo1.veiculo.dto.api.VeiculoResponseDTO;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;

public class VeiculoResponseMapper {
    public static VeiculoResponseDTO toDTO(Veiculo veiculo) {
        return new VeiculoResponseDTO(
                veiculo.getModelo(),
                veiculo.getPlaca(),
                veiculo.getValorDiaria(),
                veiculo.getDisponivel(),
                veiculo.getTipo()
        );
    }
}
