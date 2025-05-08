package tech.ada.projeto_ada.usuario.dto.mapper;

import tech.ada.projeto_ada.usuario.dto.CriarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.model.Usuario;

import java.time.LocalDateTime;

public class CriarUsuarioRequestMapper {
    public static Usuario toEntity(CriarUsuarioRequestDTO usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        return usuario;
    }
}
