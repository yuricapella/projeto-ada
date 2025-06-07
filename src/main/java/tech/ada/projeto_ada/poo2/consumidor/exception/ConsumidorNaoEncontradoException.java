package tech.ada.projeto_ada.poo2.consumidor.exception;

public class ConsumidorNaoEncontradoException extends RuntimeException{
    public ConsumidorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ConsumidorNaoEncontradoException(Long id) {
        super("Consumidor com id " + id + " não encontrado.");
    }

}
