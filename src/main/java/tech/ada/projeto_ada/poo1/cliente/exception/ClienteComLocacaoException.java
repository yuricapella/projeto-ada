package tech.ada.projeto_ada.poo1.cliente.exception;

public class ClienteComLocacaoException extends RuntimeException{
    public ClienteComLocacaoException(String mensagem) {
        super(mensagem);
    }

    public ClienteComLocacaoException(Long id) {
        super("Não é possível excluir o cliente " + id + " pois existem locações associadas.");
    }

}
