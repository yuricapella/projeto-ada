package tech.ada.projeto_ada.poo1.locacao.model;

import jakarta.persistence.*;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.usuario.model.Usuario;

import java.time.LocalDateTime;

@Entity
@Table(name = "LOCACAO")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Column(name = "dias_locacao", nullable = false)
    private Integer diasDeLocacao;

    @Column(name = "preco_total", nullable = false)
    private Double precoTotalLocacao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_finalizacao")
    private LocalDateTime dataFinalizacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Locacao() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Locacao(Cliente cliente, Veiculo veiculo, Integer diasDeLocacao) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.diasDeLocacao = diasDeLocacao;
        this.dataCriacao = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    public Integer getDiasDeLocacao() { return diasDeLocacao; }
    public void setDiasDeLocacao(Integer diasDeLocacao) { this.diasDeLocacao = diasDeLocacao; }
    public Double getPrecoTotalLocacao() { return precoTotalLocacao; }
    public void setPrecoTotalLocacao(Double precoTotalLocacao) { this.precoTotalLocacao = precoTotalLocacao; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
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

    public void setId(Long id) {
        this.id = id;
    }
}

