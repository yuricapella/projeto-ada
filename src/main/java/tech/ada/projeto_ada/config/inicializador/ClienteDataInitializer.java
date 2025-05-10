package tech.ada.projeto_ada.config.inicializador;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.ada.projeto_ada.poo1.model.Cliente;
import tech.ada.projeto_ada.poo1.repository.ClienteRepository;
import tech.ada.projeto_ada.poo1.util.TipoCliente;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClienteDataInitializer {

    @Bean
    CommandLineRunner initClientes(ClienteRepository repository) {
        return args -> {
            List<Cliente> clientes = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {
                clientes.add(new Cliente("Nome " + i, TipoCliente.PESSOA_FISICA, "Rua tal " + i, "00 90000-000" + i));
            }

            for (int i = 1; i <= 5; i++) {
                clientes.add(new Cliente("Empresa " + i, TipoCliente.PESSOA_JURIDICA, "Rua tal " + i, "11 90000-000" + i));
            }

            repository.saveAll(clientes);
        };
    }
}
