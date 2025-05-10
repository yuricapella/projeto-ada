package tech.ada.projeto_ada.exception;

public class ClienteNaoEncontradoException extends RuntimeException{
    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ClienteNaoEncontradoException(Long id) {
        super("Usuário com id " + id + " não encontrado.");
    }

}
