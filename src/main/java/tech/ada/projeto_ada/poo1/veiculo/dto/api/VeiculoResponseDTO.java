package tech.ada.projeto_ada.poo1.veiculo.dto.api;

import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;

public class VeiculoResponseDTO {
    private String modelo;
    private String placa;
    private Double valorDiaria;
    private Boolean disponivel;
    private TipoVeiculo tipo;

    public VeiculoResponseDTO() {}

    public VeiculoResponseDTO(String modelo, String placa, Double valorDiaria, Boolean disponivel, TipoVeiculo tipo) {
        this.modelo = modelo;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }
}
