package tech.ada.projeto_ada.poo1.locacao.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CriarLocacaoRequestDTO {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long veiculoId;

    @NotNull
    @Min(1)
    private Integer diasDeLocacao;

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Long getVeiculoId() { return veiculoId; }
    public void setVeiculoId(Long veiculoId) { this.veiculoId = veiculoId; }

    public Integer getDiasDeLocacao() { return diasDeLocacao; }
    public void setDiasDeLocacao(Integer diasDeLocacao) { this.diasDeLocacao = diasDeLocacao; }
}
