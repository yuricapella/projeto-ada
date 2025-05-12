package tech.ada.projeto_ada.poo1.veiculo.dto.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;

public class AtualizarVeiculoRequestDTO {
    @NotBlank(message = "Modelo não pode ser nulo.")
    private String modelo;

    @NotBlank(message = "Placa não pode ser nula.")
    private String placa;

    @NotNull(message = "Valor da diária não pode ser nulo.")
    private Double valorDiaria;

    @NotNull(message = "Disponibilidade deve ser informada.")
    private Boolean disponivel;

    @NotNull(message = "Tipo do veículo deve ser informado.")
    private TipoVeiculo tipo;

    public AtualizarVeiculoRequestDTO() {}

    public AtualizarVeiculoRequestDTO(String modelo, String placa, Double valorDiaria, Boolean disponivel, TipoVeiculo tipo) {
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
