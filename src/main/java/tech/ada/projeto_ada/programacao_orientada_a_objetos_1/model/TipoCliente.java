package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model;

public enum TipoCliente {
    PESSOA_FISICA("CPF"),
    PESSOA_JURIDICA("CNPJ");

    private final String identificador;

    TipoCliente(String identificador) {
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }
}