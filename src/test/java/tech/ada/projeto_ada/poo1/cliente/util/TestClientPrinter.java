package tech.ada.projeto_ada.poo1.cliente.util;

import tech.ada.projeto_ada.poo1.cliente.dto.ClienteResponseDTO;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;

public class TestClientPrinter {
    public static void printClientes(String titulo, Iterable<Cliente> clientes) {
        System.out.println(titulo + ":");
        for (Cliente cliente : clientes) {
            System.out.printf(
                    "  - Nome: %s | Documento: %s | Endereço: %s | Telefone: %s%n",
                    cliente.getNome(),
                    cliente.getDocumento(),
                    cliente.getEndereco(),
                    cliente.getTelefone()
            );
        }
    }

    public static void printCliente(String titulo, Cliente cliente) {
        System.out.println(titulo + ":");
        System.out.printf(
            "  - ID: %s | Nome: %s | Documento: %s | Endereço: %s | Telefone: %s%n",
            cliente.getId(),
            cliente.getNome(),
            cliente.getDocumento(),
            cliente.getEndereco(),
            cliente.getTelefone()
        );

    }

    public static void printCliente(String titulo, ClienteResponseDTO cliente) {
        System.out.println(titulo + ":");
        System.out.printf(
                "  - Nome: %s | Documento: %s | Endereço: %s | Telefone: %s%n",
                cliente.getNome(),
                cliente.getDocumento(),
                cliente.getEndereco(),
                cliente.getTelefone()
        );

    }
}
