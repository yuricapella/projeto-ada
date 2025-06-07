package tech.ada.projeto_ada.poo2.consumidor.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo2.consumidor.model.Consumidor;
import tech.ada.projeto_ada.poo2.consumidor.repository.ConsumidorRepository;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

@Service
public class CriarConsumidorService {
    private final ConsumidorRepository repository;
    private final UsuarioRepository usuarioRepository;

    public CriarConsumidorService(ConsumidorRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public Consumidor criarConsumidor(Consumidor consumidor) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();
        consumidor.setUsuario(usuario);
        return repository.save(consumidor);
    }
}
