package tech.ada.projeto_ada.config.inicializador;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;
import tech.ada.projeto_ada.poo1.cliente.util.TipoCliente;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClienteDataInitializer {

    @Bean
    CommandLineRunner initClientes(ClienteRepository repository) {
        return args -> {
            List<Cliente> clientes = gerarClientes(1, 1);
            repository.saveAll(clientes);
        };
    }

    private List<Cliente> gerarClientes(int qtdPessoaFisica, int qtdPessoaJuridica) {
        List<Cliente> clientes = new ArrayList<>();

        for (int i = 1; i <= qtdPessoaFisica; i++) {
            clientes.add(new Cliente(
                    "Cliente PF " + i,
                    TipoCliente.PESSOA_FISICA,
                    "Rua PF " + i,
                    "11 91111-000" + i
            ));
        }

        for (int i = 1; i <= qtdPessoaJuridica; i++) {
            clientes.add(new Cliente(
                    "Cliente PJ " + i,
                    TipoCliente.PESSOA_JURIDICA,
                    "Rua PJ " + i,
                    "22 92222-000" + i
            ));
        }

        return clientes;
    }
}

