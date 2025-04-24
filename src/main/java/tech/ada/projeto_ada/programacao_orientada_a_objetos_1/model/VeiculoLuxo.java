package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "VEICULO_LUXO")
public abstract class VeiculoLuxo extends Veiculo implements ServicoLuxo {
    
    public VeiculoLuxo() {
        // Construtor vazio necessário para JPA
    }
    
    public VeiculoLuxo(String modelo, String placa, double valorDiaria, boolean disponivel, TipoVeiculo tipo) {
        super(modelo, placa, valorDiaria, disponivel, tipo);
    }
    
    @Override
    public String oferecerServicoDeLuxo() {
        return "O serviço de luxo possui:\n" +
                "Banco de couro, motorista particular, portas automáticas e muito mais!!";
    }
}