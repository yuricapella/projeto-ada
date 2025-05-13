package tech.ada.projeto_ada.exception;

public class VeiculoIndisponivelException extends RuntimeException{
    public VeiculoIndisponivelException(String mensagem) {
        super(mensagem);
    }

    public VeiculoIndisponivelException(Long id) {
        super("Veiculo com id " + id + " indisponivel para locação.");
    }

}
