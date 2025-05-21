package tech.ada.projeto_ada.poo1.locacao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CriarLocacaoRequestDTO {

    @NotNull(message = "O ID do veículo é obrigatório.")
    @Positive(message = "O ID do veículo deve ser maior que zero.")
    private Long veiculoId;

    @NotNull(message = "O ID do cliente é obrigatório.")
    @Positive(message = "O ID do cliente deve ser maior que zero.")
    private Long clienteId;

    @NotNull(message = "O número de dias de locação é obrigatório.")
    @Positive(message = "O número de dias de locação deve ser maior que zero.")
    private Integer diasDeLocacao;

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Long getVeiculoId() { return veiculoId; }
    public void setVeiculoId(Long veiculoId) { this.veiculoId = veiculoId; }

    public Integer getDiasDeLocacao() { return diasDeLocacao; }
    public void setDiasDeLocacao(Integer diasDeLocacao) { this.diasDeLocacao = diasDeLocacao; }
}
