package tech.ada.projeto_ada.poo1.locacao.dto;

public class LocacaoResponseDTO {

    private Long veiculoId;
    private Long clienteId;
    private Integer diasDeLocacao;

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getDiasDeLocacao() {
        return diasDeLocacao;
    }

    public void setDiasDeLocacao(Integer diasDeLocacao) {
        this.diasDeLocacao = diasDeLocacao;
    }
}
