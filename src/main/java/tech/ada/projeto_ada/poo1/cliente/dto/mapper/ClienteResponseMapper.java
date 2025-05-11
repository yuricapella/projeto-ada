package tech.ada.projeto_ada.poo1.cliente.dto.mapper;

import tech.ada.projeto_ada.poo1.cliente.dto.ClienteResponseDTO;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;

public class ClienteResponseMapper {
    public static ClienteResponseDTO toClienteDTO(Cliente cliente){
        ClienteResponseDTO clienteDTO = new ClienteResponseDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setDocumento(cliente.getDocumento());
        clienteDTO.setEndereco(cliente.getEndereco());
        clienteDTO.setTelefone(cliente.getTelefone());
        return clienteDTO;
    }
}
