package tech.ada.projeto_ada.poo2.consumidor.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo2.consumidor.exception.ConsumidorNaoEncontradoException;
import tech.ada.projeto_ada.poo2.consumidor.model.Consumidor;
import tech.ada.projeto_ada.poo2.consumidor.repository.ConsumidorRepository;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BuscarConsumidorService {
    private final ConsumidorRepository consumidorRepository;
    private final UsuarioRepository usuarioRepository;

    public BuscarConsumidorService(ConsumidorRepository consumidorRepository, UsuarioRepository usuarioRepository) {
        this.consumidorRepository = consumidorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Consumidor> buscarTodosConsumidores() {
        return consumidorRepository.findAll();
    }

    public Consumidor buscarConsumidorPorId(Long id) {
        Optional<Consumidor> consumidorOptional = consumidorRepository.findById(id);
        return consumidorOptional
                .orElseThrow(() -> new ConsumidorNaoEncontradoException(id));
    }

    public List<Consumidor> buscarConsumidoresPorUsuarioEAtivo(boolean ativo) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();
        return consumidorRepository.findByUsuarioAndAtivo(usuario, ativo);
    }
}
