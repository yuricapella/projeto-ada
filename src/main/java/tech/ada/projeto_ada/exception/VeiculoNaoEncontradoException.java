package tech.ada.projeto_ada.exception;

public class VeiculoNaoEncontradoException extends RuntimeException{
    public VeiculoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public VeiculoNaoEncontradoException(Long id) {
        super("Veiculo com id " + id + " não encontrado.");
    }

}
