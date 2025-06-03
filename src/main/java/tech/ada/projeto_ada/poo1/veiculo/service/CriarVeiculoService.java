package tech.ada.projeto_ada.poo1.veiculo.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

@Service
public class CriarVeiculoService {
    private final VeiculoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public CriarVeiculoService(VeiculoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public Veiculo criarVeiculo(Veiculo veiculo) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();
        veiculo.setUsuario(usuario);
        return repository.save(veiculo);
    }
}
