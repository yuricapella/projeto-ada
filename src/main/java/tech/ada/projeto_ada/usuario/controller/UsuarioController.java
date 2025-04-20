package tech.ada.projeto_ada.usuario.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.projeto_ada.usuario.dto.CriarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.dto.UsuarioDTO;
import tech.ada.projeto_ada.usuario.dto.mapper.CriarUsuarioRequestMapper;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.service.AtualizarUsuarioService;
import tech.ada.projeto_ada.usuario.service.BuscarUsuarioService;
import tech.ada.projeto_ada.usuario.service.CriarUsuarioService;
import tech.ada.projeto_ada.usuario.service.DeletarUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final BuscarUsuarioService buscarUsuarioService;
    private final CriarUsuarioService criarUsuarioService;
    private final AtualizarUsuarioService atualizarUsuarioService;
    private final DeletarUsuarioService deletarUsuarioService;

    public UsuarioController(BuscarUsuarioService buscarUsuarioService, CriarUsuarioService criarUsuarioService, AtualizarUsuarioService atualizarUsuarioService, DeletarUsuarioService deletarUsuarioService) {
        this.buscarUsuarioService = buscarUsuarioService;
        this.criarUsuarioService = criarUsuarioService;
        this.atualizarUsuarioService = atualizarUsuarioService;
        this.deletarUsuarioService = deletarUsuarioService;
    }

    @GetMapping
    public List<UsuarioDTO> buscarTodosUsuarios() {
        return buscarUsuarioService.buscarTodosUsuarios();
    }

    @GetMapping(path = "/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable(value = "id") Long id) {
        return buscarUsuarioService.buscarUsuarioPorId(id);
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid CriarUsuarioRequestDTO usuario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criarUsuarioService.criarUsuario(CriarUsuarioRequestMapper.toEntity(usuario)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable(value = "id") Long id,
                                                    @RequestBody @Valid CriarUsuarioRequestDTO usuario) {

        atualizarUsuarioService.atualizarUsuario(usuario,id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable(value = "id") Long id) {
        deletarUsuarioService.deletarUsuarioPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
