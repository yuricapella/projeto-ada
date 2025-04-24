package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOCACAO")
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
    
    private int periodoLocacao;
    private double valorLocacao;
    private LocalDateTime dataLocacao;
    
    public Locacao() {
        // Construtor vazio necessário para JPA
        this.dataLocacao = LocalDateTime.now();
    }
    
    public Locacao(Cliente cliente, Veiculo veiculo, int periodoLocacao) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.periodoLocacao = periodoLocacao;
        this.valorLocacao = calcularValorLocacao(periodoLocacao, veiculo.getValorDiaria());
        this.dataLocacao = LocalDateTime.now();
    }
    
    private double calcularValorLocacao(int periodoLocacao, double valorDiariaVeiculo) {
        return periodoLocacao * valorDiariaVeiculo;
    }
    
    // Getters e setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public int getPeriodoLocacao() {
        return periodoLocacao;
    }
    
    public void setPeriodoLocacao(int periodoLocacao) {
        this.periodoLocacao = periodoLocacao;
    }
    
    public double getValorLocacao() {
        return valorLocacao;
    }
    
    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
    
    public LocalDateTime getDataLocacao() {
        return dataLocacao;
    }
    
    public void setDataLocacao(LocalDateTime dataLocacao) {
        this.dataLocacao = dataLocacao;
    }
    
    @Override
    public String toString() {
        return String.format(
                """
                ----------------------------------
                    Locação %d
                    
                Informações do Cliente: %s
                Informações do Veiculo: %s
                    Periodo de locação: %s
                    Valor da Locação: %s
                """,
                getId(), cliente, veiculo, periodoLocacao, valorLocacao
        ).indent(1);
    }
}