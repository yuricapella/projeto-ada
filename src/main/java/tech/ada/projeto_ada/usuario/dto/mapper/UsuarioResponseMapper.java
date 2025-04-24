package tech.ada.projeto_ada.usuario.dto.mapper;

import tech.ada.projeto_ada.usuario.dto.UsuarioResponseDTO;
import tech.ada.projeto_ada.usuario.model.Usuario;

public class UsuarioResponseMapper {

    public static UsuarioResponseDTO toUsuarioDTO(Usuario usuario) {
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        return usuarioDTO;
    }

}
