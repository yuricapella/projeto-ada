package tech.ada.projeto_ada.poo2.consumidor.util;

public enum TipoConsumidor {
    PESSOA_FISICA("CPF"),
    PESSOA_JURIDICA("CNPJ");


    private final String identificador;

    TipoConsumidor(String identificador) {
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }
}
