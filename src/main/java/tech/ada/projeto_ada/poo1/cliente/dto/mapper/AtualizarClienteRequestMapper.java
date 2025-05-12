package tech.ada.projeto_ada.poo1.cliente.dto.mapper;

import tech.ada.projeto_ada.poo1.cliente.dto.AtualizarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;

import java.time.LocalDateTime;

public class AtualizarClienteRequestMapper{

    public static void updateEntity(Cliente clienteExistente, AtualizarClienteRequestDTO clienteAtualizado) {
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setDocumento(clienteAtualizado.getDocumento());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteExistente.setDataAtualizacao(LocalDateTime.now());
    }
}
