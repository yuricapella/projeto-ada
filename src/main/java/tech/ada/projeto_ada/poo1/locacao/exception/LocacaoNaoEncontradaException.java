package tech.ada.projeto_ada.poo1.locacao.exception;

public class LocacaoNaoEncontradaException extends RuntimeException{
    public LocacaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public LocacaoNaoEncontradaException(Long id) {
        super("Locação com id " + id + " não encontrada.");
    }

}
