package tech.ada.projeto_ada.poo1.locacao.dto.mapper;

import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.locacao.dto.LocacaoDTO;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;

public class LocacaoMapper {

    public static Locacao toEntity(LocacaoDTO dto, Cliente cliente, Veiculo veiculo) {
        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setVeiculo(veiculo);
        locacao.setDiasDeLocacao(dto.getDiasDeLocacao());
        return locacao;
    }

    public static LocacaoDTO toDTO(Locacao locacao) {
        LocacaoDTO dto = new LocacaoDTO();
        dto.setClienteId(locacao.getCliente().getId());
        dto.setVeiculoId(locacao.getVeiculo().getId());
        dto.setDiasDeLocacao(locacao.getDiasDeLocacao());
        return dto;
    }
}