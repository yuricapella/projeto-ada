package tech.ada.projeto_ada.poo1.veiculo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoClasseVeiculo;

public class CriarVeiculoRequestDTO {

    @NotBlank(message = "Modelo não pode ser nulo.")
    private String modelo;

    @NotBlank(message = "Placa não pode ser nula.")
    private String placa;

    @NotNull(message = "Valor da diária não pode ser nulo.")
    @Positive(message = "Valor da diária não pode ser 0 ou negativo.")
    private Double valorDiaria;

    @NotNull(message = "Disponibilidade deve ser informada.")
    private Boolean disponivel;

    @NotNull(message = "Tipo do veículo deve ser informado.")
    private TipoVeiculo tipo;

    @NotNull(message = "Tipo da classe do veículo deve ser informado.")
    private TipoClasseVeiculo tipoClasse;

    public CriarVeiculoRequestDTO() {

    }

    public CriarVeiculoRequestDTO(String modelo, String placa, Double valorDiaria, Boolean disponivel, TipoVeiculo tipo) {
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

    public TipoClasseVeiculo getTipoClasse() {
        return tipoClasse;
    }

    public void setTipoClasse(TipoClasseVeiculo tipoClasse) {
        this.tipoClasse = tipoClasse;
    }
}

