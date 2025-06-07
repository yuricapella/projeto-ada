package tech.ada.projeto_ada.poo2.consumidor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tech.ada.projeto_ada.poo2.consumidor.util.TipoConsumidor;

public class AtualizarConsumidorRequestDTO {

    @NotBlank(message = "Nome não pode ser nulo.")
    @Size(min = 3, message = "Nome deve conter no mínimo 3 letras.")
    private String nome;

    @NotBlank(message = "Documento não pode ser nulo.")
    private String documento;

    @NotBlank(message = "Email não pode ser nulo.")
    @Email(message = "Email inválido.")
    private String email;

    @NotNull(message = "Tipo do consumidor não pode ser nulo.")
    private TipoConsumidor tipo;

    public AtualizarConsumidorRequestDTO() {
    }

    public AtualizarConsumidorRequestDTO(String nome, String documento, String email, TipoConsumidor tipo) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoConsumidor getTipo() {
        return tipo;
    }

    public void setTipo(TipoConsumidor tipo) {
        this.tipo = tipo;
    }
}
