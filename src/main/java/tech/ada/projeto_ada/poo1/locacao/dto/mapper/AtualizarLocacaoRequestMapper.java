package tech.ada.projeto_ada.poo1.locacao.dto.mapper;

import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.locacao.dto.AtualizarLocacaoRequestDTO;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;

import java.time.LocalDateTime;

public class AtualizarLocacaoRequestMapper {

    public static void updateEntity(Locacao locacaoExistente, AtualizarLocacaoRequestDTO dto, Cliente cliente, Veiculo veiculo) {
        locacaoExistente.setCliente(cliente);
        locacaoExistente.setVeiculo(veiculo);
        locacaoExistente.setDiasDeLocacao(dto.getDiasDeLocacao());
        locacaoExistente.setPrecoTotalLocacao(veiculo.getValorDiaria() * dto.getDiasDeLocacao());
        locacaoExistente.setDataFinalizacao(locacaoExistente.getDataCriacao().plusDays(dto.getDiasDeLocacao()));
        locacaoExistente.setDataAtualizacao(LocalDateTime.now());
    }
}
