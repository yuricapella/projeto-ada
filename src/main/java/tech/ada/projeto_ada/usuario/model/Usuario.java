package tech.ada.projeto_ada.usuario.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import tech.ada.projeto_ada.usuario.util.FormataData;

import java.time.LocalDateTime;

    @Entity
    @Table(name = "USUARIO")

    public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false)
        private String nome;
        @Column(unique = true, nullable = false)
        private String email;
        private String senha;
        private int idade;

        @JsonFormat(pattern = FormataData.FORMATO)
        private LocalDateTime dataCriacao;
        @JsonFormat(pattern = FormataData.FORMATO)
        private LocalDateTime dataAtualizacao;

        public Usuario() {
            this.dataCriacao = LocalDateTime.now();
        }

        public Usuario(Long id, String nome, String email, String senha, int idade, LocalDateTime dataCriacao) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.idade = idade;
            this.dataCriacao = dataCriacao;
        }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
