package tech.ada.projeto_ada.logica_programacao.exception;

public class SalarioInvalidoException extends RuntimeException{
    public SalarioInvalidoException(String mensagem) {
        super(mensagem);
    }

    public SalarioInvalidoException(Double salario) {
        super("Salario: " + salario + " incorreto, deve ser maior que 0");
    }

}
