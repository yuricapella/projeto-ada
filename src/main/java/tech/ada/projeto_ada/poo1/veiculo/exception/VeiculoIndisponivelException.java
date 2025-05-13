package tech.ada.projeto_ada.poo1.veiculo.exception;

public class VeiculoIndisponivelException extends RuntimeException{
    public VeiculoIndisponivelException(String mensagem) {
        super(mensagem);
    }

    public VeiculoIndisponivelException(Long id) {
        super("Veiculo com id " + id + " indisponivel para locação.");
    }

}
