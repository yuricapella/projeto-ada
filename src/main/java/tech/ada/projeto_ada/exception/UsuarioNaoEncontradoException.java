package tech.ada.projeto_ada.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário com id " + id + " não encontrado.");
    }

}
