package tech.ada.projeto_ada.poo1.veiculo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.util.FormataData;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "VEICULO")
public abstract class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String modelo;
    @Column(unique = true, nullable = false)
    private String placa;
    private double valorDiaria;
    private boolean disponivel;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipo;

    @JsonFormat(pattern = FormataData.PADRAO_DATA_HORA)
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = FormataData.PADRAO_DATA_HORA)
    private LocalDateTime dataAtualizacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Veiculo() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Veiculo(String modelo, String placa, double valorDiaria, boolean disponivel, TipoVeiculo tipo) {
        this.modelo = modelo;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
        this.tipo = tipo;
        this.dataCriacao = LocalDateTime.now();
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
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

