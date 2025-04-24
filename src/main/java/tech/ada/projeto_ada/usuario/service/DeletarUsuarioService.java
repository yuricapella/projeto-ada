package tech.ada.projeto_ada.usuario.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

@Service
public class DeletarUsuarioService {
    private final BuscarUsuarioService buscarUsuarioService;
    private final UsuarioRepository repository;

    public DeletarUsuarioService(BuscarUsuarioService buscarUsuarioService, UsuarioRepository repository) {
        this.buscarUsuarioService = buscarUsuarioService;
        this.repository = repository;
    }

    public void deletarUsuarioPorId(Long id) {
        buscarUsuarioService.buscarUsuarioPorId(id);
        repository.deleteById(id);
    }
}
