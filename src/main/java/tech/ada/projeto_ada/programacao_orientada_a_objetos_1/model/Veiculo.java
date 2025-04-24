package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model;

import jakarta.persistence.*;
import tech.ada.projeto_ada.usuario.model.Usuario;

@Entity
@Table(name = "VEICULO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String modelo;
    
    @Column(unique = true)
    private String placa;
    
    private double valorDiaria;
    private boolean disponivel;
    
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    public Veiculo() {
        // Construtor vazio necessário para JPA
    }
    
    public Veiculo(String modelo, String placa, double valorDiaria, boolean disponivel, TipoVeiculo tipo) {
        this.modelo = modelo;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
        this.tipo = tipo;
    }
    
    // Getters e setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
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
    
    public boolean isDisponivel() {
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
    
    @Override
    public String toString() {
        return String.format(
                """
                    %s
                    Placa: %s
                    Valor da diária: %s
                    Disponibilidade: %s
                """,
                getModelo(), getPlaca(), getValorDiaria(), isDisponivel()
        ).indent(4);
    }
}