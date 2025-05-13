package tech.ada.projeto_ada.poo1.cliente.exception;

public class ClienteNaoEncontradoException extends RuntimeException{
    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ClienteNaoEncontradoException(Long id) {
        super("Cliente com id " + id + " não encontrado.");
    }

}
