package tech.ada.projeto_ada.poo1.veiculo.dto.mapper;

import tech.ada.projeto_ada.poo1.veiculo.dto.AtualizarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;

import java.time.LocalDateTime;

public class AtualizarVeiculoRequestMapper {

    public static void updateEntity(Veiculo veiculoExistente, AtualizarVeiculoRequestDTO dto) {
        veiculoExistente.setModelo(dto.getModelo());
        veiculoExistente.setPlaca(dto.getPlaca());
        veiculoExistente.setValorDiaria(dto.getValorDiaria());
        veiculoExistente.setDisponivel(dto.getDisponivel());
        veiculoExistente.setTipo(dto.getTipo());
        veiculoExistente.setDataAtualizacao(LocalDateTime.now());
    }
}
