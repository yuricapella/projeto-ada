package tech.ada.projeto_ada.poo1.cliente.util;

import tech.ada.projeto_ada.poo1.cliente.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteCreator {
    public static Cliente criarCliente() {
        return new Cliente("Nome", TipoCliente.PESSOA_FISICA, "RUA TAL 1", "48934567890");
    }

    public static List<Cliente> criarClientes(int quantidade) {
        List<Cliente> clientes = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            Cliente cliente = new Cliente(
                    "Nome" + i,
                    TipoCliente.PESSOA_FISICA,
                    "Rua Exemplo " + i,
                    "4893456789" + i
            );
            clientes.add(cliente);
        }
        return clientes;
    }
}
