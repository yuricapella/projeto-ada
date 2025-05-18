package tech.ada.projeto_ada.perfil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("dev")
class ConfiguracaoPerfilDevTest {

    @Autowired
    private Environment environment;

    @Test
    void deveUsarConfiguracoesDoPerfilDev() {
        String port = environment.getProperty("server.port");
        String datasourceUrl = environment.getProperty("spring.datasource.url");

        System.out.println("Porta configurada: " + port);
        System.out.println("URL do datasource: " + datasourceUrl);

        assertEquals("8081", port);
        assertEquals("jdbc:h2:mem:test2", datasourceUrl);
    }
}
