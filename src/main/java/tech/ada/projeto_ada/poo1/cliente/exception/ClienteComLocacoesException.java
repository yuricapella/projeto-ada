package tech.ada.projeto_ada.poo1.cliente.exception;

public class ClienteComLocacoesException extends RuntimeException{
    public ClienteComLocacoesException(String mensagem) {
        super(mensagem);
    }

    public ClienteComLocacoesException(Long id) {
        super("Não é possível excluir o cliente " + id + " pois existem locações associadas.");
    }

}
