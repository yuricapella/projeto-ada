package tech.ada.projeto_ada.poo1.locacao.dto.mapper;

import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.locacao.dto.CriarLocacaoRequestDTO;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;

public class CriarLocacaoRequestMapper {
    public Locacao toEntity(CriarLocacaoRequestDTO dto, Cliente cliente, Veiculo veiculo) {
        return new Locacao(cliente, veiculo, dto.getDiasDeLocacao());
    }
}
