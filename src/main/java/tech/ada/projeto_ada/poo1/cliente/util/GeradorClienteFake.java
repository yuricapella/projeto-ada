package tech.ada.projeto_ada.poo1.cliente.util;

import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.BuscarClienteService;
import tech.ada.projeto_ada.poo1.cliente.service.CriarClienteService;

import java.util.ArrayList;
import java.util.List;

public class GeradorClienteFake {

    private final CriarClienteService criarClienteService;
    private final BuscarClienteService buscarClienteService;

    public GeradorClienteFake(CriarClienteService criarClienteService, BuscarClienteService buscarClienteService) {
        this.criarClienteService = criarClienteService;
        this.buscarClienteService = buscarClienteService;
    }

    public List<Cliente> gerarClientes(int quantidadePessoaFisica, int quantidadePessoaJuridica) {
        List<Cliente> clientesGerados = new ArrayList<>();
        Long maiorId = buscarClienteService.obterMaiorId();

        for (long i = maiorId + 1; i <= maiorId + quantidadePessoaFisica; i++) {
            Cliente cliente = new Cliente(
                    "Cliente Pessoa Fisica " + i,
                    TipoCliente.PESSOA_FISICA,
                    "Rua Pessoa Fisica " + i,
                    "11 91111-000" + i
            );
            clientesGerados.add(criarClienteService.criarCliente(cliente));
        }

        for (long i = maiorId + 1; i <= maiorId + quantidadePessoaJuridica; i++) {
            Cliente cliente = new Cliente(
                    "Cliente Pessoa Juridica " + i,
                    TipoCliente.PESSOA_JURIDICA,
                    "Rua Pessoa Juridica " + i,
                    "22 92222-000" + i
            );
            clientesGerados.add(criarClienteService.criarCliente(cliente));
        }

        return clientesGerados;
    }
}
