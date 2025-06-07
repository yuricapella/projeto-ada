package tech.ada.projeto_ada.poo2.consumidor.exception;

public class ConsumidorJaEstaInativoException extends RuntimeException{
    public ConsumidorJaEstaInativoException(String mensagem) {
        super(mensagem);
    }

    public ConsumidorJaEstaInativoException(Long id) {
        super("Consumidor com id " + id + " já está inativo.");
    }

}
