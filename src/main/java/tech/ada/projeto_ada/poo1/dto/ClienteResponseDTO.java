package tech.ada.projeto_ada.poo1.dto;

public class ClienteResponseDTO {
    private String nome;
    private String documento;
    private String endereco;
    private String telefone;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(String nome, String documento, String endereco, String telefone) {
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
