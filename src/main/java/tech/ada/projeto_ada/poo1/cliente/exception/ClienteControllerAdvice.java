package tech.ada.projeto_ada.poo1.cliente.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.projeto_ada.poo1.cliente.controller.ClienteViewController;
import org.springframework.ui.Model;

@ControllerAdvice(assignableTypes = ClienteViewController.class)
public class ClienteControllerAdvice {

    @ExceptionHandler(ClienteComLocacaoException.class)
    public String handleClienteComLocacao(ClienteComLocacaoException ex, Model model) {
        model.addAttribute("erroClienteComLocacao", ex.getMessage());
        return "poo1/cliente/listar";
    }
}