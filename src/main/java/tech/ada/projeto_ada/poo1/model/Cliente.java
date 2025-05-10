package tech.ada.projeto_ada.poo1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import tech.ada.projeto_ada.poo1.util.TipoCliente;
import tech.ada.projeto_ada.usuario.util.FormataData;

import java.time.LocalDateTime;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
    private String nome;
    private String documento;
    private String endereco;
    private String telefone;
    private static long contadorCliente = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente;

    @JsonFormat(pattern = FormataData.PADRAO_DATA_HORA)
    private LocalDateTime dataCriacao;
    @JsonFormat(pattern = FormataData.PADRAO_DATA_HORA)
    private LocalDateTime dataAtualizacao;

    public Cliente() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Cliente(String nome, TipoCliente tipo, String endereco, String telefone) {
        this.nome = nome;
        this.documento = tipo.getIdentificador();
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataCriacao = LocalDateTime.now();
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

    public static long getContadorCliente() {
        return contadorCliente;
    }

    public static void setContadorCliente(long contadorCliente) {
        Cliente.contadorCliente = contadorCliente;
    }

    public long getIdCliente() {
        return idCliente;
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
}
