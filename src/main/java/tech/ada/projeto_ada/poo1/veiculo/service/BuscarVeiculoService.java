package tech.ada.projeto_ada.poo1.veiculo.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoNaoEncontradoException;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BuscarVeiculoService {
    private final VeiculoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final VeiculoRepository veiculoRepository;

    public BuscarVeiculoService(VeiculoRepository repository, UsuarioRepository usuarioRepository, VeiculoRepository veiculoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> buscarTodosVeiculos() {
        return repository.findAll();
    }

    public Veiculo buscarVeiculoPorId(Long id) {
        Optional<Veiculo> veiculoOptional = repository.findById(id);
        return veiculoOptional
                .orElseThrow(() -> new VeiculoNaoEncontradoException(id));
    }

    public List<Veiculo> buscarVeiculoDoUsuarioLogado() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();
        return veiculoRepository.findByUsuario(usuario);
    }
}
