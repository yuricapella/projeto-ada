package tech.ada.projeto_ada.usuario.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuariosRepository;

@Service
public class CriarUsuarioService {
    private final UsuariosRepository repository;

    public CriarUsuarioService(UsuariosRepository repository) {
        this.repository = repository;
    }

    public Usuario criarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

}
