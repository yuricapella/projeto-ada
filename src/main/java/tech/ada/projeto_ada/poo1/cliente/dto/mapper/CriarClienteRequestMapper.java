package tech.ada.projeto_ada.poo1.cliente.dto.mapper;

import tech.ada.projeto_ada.poo1.cliente.dto.CriarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;

public class CriarClienteRequestMapper {
    public static Cliente toEntity(CriarClienteRequestDTO clienteRequest) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        cliente.setDocumento(clienteRequest.getDocumento());
        cliente.setEndereco(clienteRequest.getEndereco());
        cliente.setTelefone(clienteRequest.getTelefone());
        return cliente;
    }
}
