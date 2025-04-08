package tech.ada.projeto_ada.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.model.Usuario;
import tech.ada.projeto_ada.repository.UsuariosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuariosRepository repository;

    public UsuarioService(UsuariosRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
