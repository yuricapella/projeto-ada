package tech.ada.projeto_ada.poo2.consumidor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import tech.ada.projeto_ada.poo2.consumidor.util.TipoConsumidor;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.util.FormataData;

import java.time.LocalDateTime;

@Entity
@Table(name = "CONSUMIDOR")
public class Consumidor {
    private String nome;
    @Column(unique = true, nullable = false)
    private String documento;
    private String email;
    private TipoConsumidor tipo;
    private boolean ativo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(pattern = FormataData.PADRAO_DATA_HORA)
    private LocalDateTime dataCriacao;
    @JsonFormat(pattern = FormataData.PADRAO_DATA_HORA)
    private LocalDateTime dataAtualizacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Consumidor() {
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
    }

    public Consumidor(String nome, String documento, String email, TipoConsumidor tipo) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.tipo = tipo;
        this.ativo = true;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
