package tech.ada.projeto_ada.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository repository, PasswordEncoder encoder) {
        return args -> {
            List<Usuario> usuarios = Arrays.asList(
                    new Usuario("João Silva", "joao.silva@email.com", "senha123"),
                    new Usuario("Maria Oliveira", "maria.oliveira@email.com", "senha456"),
                    new Usuario("Pedro Santos", "pedro.santos@email.com", "senha789"),
                    new Usuario("Ana Rodrigues", "ana.rodrigues@email.com", "senhaabc"),
                    new Usuario("Yuri", "yuri@yuri.com", "yuri")
            );

            usuarios.forEach(u -> {
                u.setSenha(encoder.encode(u.getSenha()));
                u.setDataCriacao(LocalDateTime.now());
            });

            repository.saveAll(usuarios);
        };
    }
}