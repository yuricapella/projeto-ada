package tech.ada.projeto_ada.poo1.cliente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CriarClienteRequestDTO {
    @NotBlank(message = "Nome não pode ser nulo.")
    @Size(min = 3, message = "Nome deve conter no mínimo 3 letras.")
    private String nome;
    @NotBlank(message = "Documento não pode ser nulo.")
    private String documento;
    @NotBlank(message = "Endereço não pode ser nulo.")
    private String endereco;
    @NotBlank(message = "Telefone não pode ser nulo.")
    @Size(min = 11, message = "Telefone deve conter no mínimo 10 dígitos. Exemplo: 11912349876")
    private String telefone;

    public CriarClienteRequestDTO() {
    }
    public CriarClienteRequestDTO(String nome, String documento, String endereco, String telefone) {
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.telefone = telefone;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
