package tech.ada.projeto_ada.poo1.cliente.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.projeto_ada.poo1.cliente.controller.ClienteViewController;
import org.springframework.ui.Model;

@ControllerAdvice(assignableTypes = ClienteViewController.class)
public class ClienteControllerAdvice {

    @ExceptionHandler(ClienteComLocacoesException.class)
    public String handleClienteComLocacoes(ClienteComLocacoesException ex, Model model) {
        model.addAttribute("erroClienteComLocacoes", ex.getMessage());
        return "poo1/cliente/listar";
    }
}