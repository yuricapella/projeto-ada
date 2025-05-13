package tech.ada.projeto_ada.poo1.veiculo.exception;

public class VeiculoNaoEncontradoException extends RuntimeException{
    public VeiculoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public VeiculoNaoEncontradoException(Long id) {
        super("Veiculo com id " + id + " não encontrado.");
    }

}
