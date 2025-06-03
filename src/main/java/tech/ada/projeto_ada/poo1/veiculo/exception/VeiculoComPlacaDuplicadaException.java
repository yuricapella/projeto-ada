package tech.ada.projeto_ada.poo1.veiculo.exception;

public class VeiculoComPlacaDuplicadaException extends RuntimeException{

    public VeiculoComPlacaDuplicadaException(String placa) {
        super("Não é possível criar o veículo com placa " + placa + " pois essa placa já está cadastrada!");
    }

}
