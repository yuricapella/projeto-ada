package tech.ada.projeto_ada.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.projeto_ada.exception.ClienteNaoEncontradoException;
import tech.ada.projeto_ada.exception.VeiculoIndisponivelException;
import tech.ada.projeto_ada.exception.VeiculoNaoEncontradoException;
import tech.ada.projeto_ada.poo1.locacao.controller.LocacaoViewController;

@ControllerAdvice(assignableTypes = LocacaoViewController.class)
public class LocacaoControllerAdvice {

    @ExceptionHandler(VeiculoIndisponivelException.class)
    public String handleVeiculoIndisponivel(VeiculoIndisponivelException ex, Model model) {
        model.addAttribute("erroVeiculoIndisponivel", ex.getMessage());
        return "poo1/locacao/cadastrar";
    }

    @ExceptionHandler(VeiculoNaoEncontradoException.class)
    public String handleVeiculoNaoEncontrado(VeiculoNaoEncontradoException ex, Model model) {
        model.addAttribute("erroVeiculoNaoEncontrado", ex.getMessage());
        return "poo1/locacao/cadastrar";
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public String handleClienteNaoEncontrado(ClienteNaoEncontradoException ex, Model model) {
        model.addAttribute("erroClienteNaoEncontrado", ex.getMessage());
        return "poo1/locacao/cadastrar";
    }
}