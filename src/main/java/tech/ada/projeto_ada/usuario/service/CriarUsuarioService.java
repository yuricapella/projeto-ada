package tech.ada.projeto_ada.usuario.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

@Service
public class CriarUsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CriarUsuarioService(UsuarioRepository repository,
                               PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(Usuario usuario) {
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
        String senhaAntiga = usuario.getSenha();
        String senhaCriptografada = passwordEncoder.encode(senhaAntiga);
        usuario.setSenha(senhaCriptografada);
        return repository.save(usuario);
    }
}