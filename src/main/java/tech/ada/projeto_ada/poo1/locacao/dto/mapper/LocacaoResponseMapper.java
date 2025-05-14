package tech.ada.projeto_ada.poo1.locacao.dto.mapper;

import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.locacao.dto.LocacaoResponseDTO;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;

public class LocacaoResponseMapper {

    public static Locacao toEntity(LocacaoResponseDTO dto, Cliente cliente, Veiculo veiculo) {
        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setVeiculo(veiculo);
        locacao.setDiasDeLocacao(dto.getDiasDeLocacao());
        return locacao;
    }

    public static LocacaoResponseDTO toDTO(Locacao locacao) {
        LocacaoResponseDTO dto = new LocacaoResponseDTO();
        dto.setClienteId(locacao.getCliente().getId());
        dto.setVeiculoId(locacao.getVeiculo().getId());
        dto.setDiasDeLocacao(locacao.getDiasDeLocacao());
        return dto;
    }
}