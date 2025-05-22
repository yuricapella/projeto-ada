package tech.ada.projeto_ada.poo1.veiculo.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.projeto_ada.exception.ErroCodigo;
import tech.ada.projeto_ada.exception.ErroPadrao;
import tech.ada.projeto_ada.poo1.veiculo.controller.VeiculoApiController;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = VeiculoApiController.class)
public class VeiculoControllerAdviceRest {

    @ExceptionHandler({VeiculoNaoEncontradoException.class})
    public ResponseEntity<ErroPadrao> handlerVeiculoNaoEncontradoException(VeiculoNaoEncontradoException ex) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setCodigoErro(ErroCodigo.VEICULO_NAO_ENCONTRADO.name());
        erroPadrao.setDataHora(LocalDateTime.now());
        erroPadrao.setMensagem(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erroPadrao);
    }

    @ExceptionHandler({VeiculoComLocacaoException.class})
    public ResponseEntity<ErroPadrao> handlerVeiculoComLocacaoException(VeiculoComLocacaoException ex) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setCodigoErro(ErroCodigo.VEICULO_COM_LOCACAO.name());
        erroPadrao.setDataHora(LocalDateTime.now());
        erroPadrao.setMensagem(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erroPadrao);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroPadrao> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setCodigoErro(ErroCodigo.PLACA_DUPLICADA.name());
        erroPadrao.setDataHora(LocalDateTime.now());
        erroPadrao.setMensagem("Placa já cadastrada no sistema.");

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erroPadrao);
    }

}
