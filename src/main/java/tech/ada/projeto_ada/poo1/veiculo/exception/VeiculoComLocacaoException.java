package tech.ada.projeto_ada.poo1.veiculo.exception;

public class VeiculoComLocacaoException extends RuntimeException{
    public VeiculoComLocacaoException(String mensagem) {
        super(mensagem);
    }

    public VeiculoComLocacaoException(Long id) {
        super("Não é possível excluir o veiculo " + id + " pois existem locações associadas.");
    }

}
