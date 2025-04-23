package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model;

import jakarta.persistence.*;
import tech.ada.projeto_ada.usuario.model.Usuario;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    
    private String nome;
    private String documento;
    private String endereco;
    private String telefone;
    
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    public Cliente() {
        // Construtor vazio necessário para JPA
    }
    
    public Cliente(String nome, TipoCliente tipo, String endereco, String telefone) {
        this.nome = nome;
        this.documento = tipo.getIdentificador();
        this.tipoCliente = tipo;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    
    // Getters e setters
    public Long getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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
    
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    
    @Override
    public String toString() {
        return String.format(
                """
                    %s
                    Documento: %s
                    Endereço: %s
                    Telefone: %s
                    Id Cliente: %d
                """,
                nome, documento, endereco, telefone, idCliente
        ).indent(4);
    }
}