package tech.ada.projeto_ada.poo1.veiculo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.projeto_ada.poo1.veiculo.controller.VeiculoViewController;

@ControllerAdvice(assignableTypes = VeiculoViewController.class)
public class VeiculoControllerAdvice {

    @ExceptionHandler(VeiculoComLocacaoException.class)
    public String handleVeiculoComLocacao(VeiculoComLocacaoException ex, Model model) {
        model.addAttribute("erroVeiculoComLocacao", ex.getMessage());
        return "poo1/veiculo/listar";
    }

    @ExceptionHandler(VeiculoComPlacaDuplicadaException.class)
    public String handleVeiculoComPlacaDuplicada(VeiculoComPlacaDuplicadaException ex, Model model) {
        model.addAttribute("erroVeiculoComPlacaDuplicada", ex.getMessage());
        return "poo1/veiculo/cadastrar";
    }

}
