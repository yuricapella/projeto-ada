package tech.ada.projeto_ada.usuario.dto;

import jakarta.validation.constraints.*;

public class CriarUsuarioRequestDTO {

    @NotBlank(message = "Nome não pode ser nulo.")
    @Size(min = 3, message = "Nome deve conter no mínimo 3 letras.")
    private String nome;

    @Email(message = "Email está fora do padrão.")
    private String email;

    @NotBlank(message = "Senha não pode ser nula.")
    @Size(min = 8, message = "Senha deve conter no mínimo 8 dígitos.")
    private String senha;

    @NotNull(message = "Idade não pode ser nula.")
    @Positive(message = "Idade não pode ser 0 ou negativa.")
    private int idade;

    public CriarUsuarioRequestDTO() {
    }

    public CriarUsuarioRequestDTO(String nome, String email, String senha, int idade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
