package tech.ada.projeto_ada.usuario.controller;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tech.ada.projeto_ada.usuario.dto.CriarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.service.CriarUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    private final PasswordEncoder passwordEncoder;
    private final CriarUsuarioService criarUsuarioService;

    public UsuarioViewController(PasswordEncoder passwordEncoder,
                                 CriarUsuarioService criarUsuarioService) {
        this.passwordEncoder = passwordEncoder;
        this.criarUsuarioService = criarUsuarioService;
    }

    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute @Valid CriarUsuarioRequestDTO usuarioDTO,
                                    RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = new Usuario(
                    usuarioDTO.getNome(),
                    usuarioDTO.getEmail(),
                    usuarioDTO.getSenha()
            );

            criarUsuarioService.criarUsuario(usuario);
            redirectAttributes.addAttribute("success", true);
            return "redirect:/login";

        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("error", true);
            redirectAttributes.addAttribute("message", "Email já cadastrado");
            return "redirect:/cadastro";
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", true);
            redirectAttributes.addAttribute("message", "Erro ao realizar cadastro");
            return "redirect:/cadastro";
        }
    }
}
