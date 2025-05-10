package tech.ada.projeto_ada.poo1.dto.mapper;

import tech.ada.projeto_ada.poo1.dto.ClienteResponseDTO;
import tech.ada.projeto_ada.poo1.model.Cliente;

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
