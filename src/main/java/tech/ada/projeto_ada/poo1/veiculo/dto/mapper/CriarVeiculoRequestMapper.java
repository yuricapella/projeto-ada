package tech.ada.projeto_ada.poo1.veiculo.dto.mapper;

import tech.ada.projeto_ada.poo1.veiculo.dto.CriarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;

public class CriarVeiculoRequestMapper {
    public static Veiculo toEntity(CriarVeiculoRequestDTO request) {
        return new Veiculo(
                request.getModelo(),
                request.getPlaca(),
                request.getValorDiaria(),
                request.getDisponivel(),
                request.getTipo()
        ) {};
    }
}
