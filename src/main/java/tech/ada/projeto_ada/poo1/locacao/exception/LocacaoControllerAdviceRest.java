package tech.ada.projeto_ada.poo1.locacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.projeto_ada.exception.ErroCodigo;
import tech.ada.projeto_ada.exception.ErroPadrao;
import tech.ada.projeto_ada.poo1.cliente.exception.ClienteNaoEncontradoException;
import tech.ada.projeto_ada.poo1.locacao.controller.LocacaoApiController;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoIndisponivelException;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoNaoEncontradoException;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = LocacaoApiController.class)
public class LocacaoControllerAdviceRest {

    @ExceptionHandler(LocacaoNaoEncontradaException.class)
    public ResponseEntity<ErroPadrao> handlerLocacaoNaoEncontradaException(LocacaoNaoEncontradaException ex) {
        ErroPadrao erro = new ErroPadrao();
        erro.setCodigoErro(ErroCodigo.LOCACAO_NAO_ENCONTRADA.name());
        erro.setDataHora(LocalDateTime.now());
        erro.setMensagem(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(VeiculoIndisponivelException.class)
    public ResponseEntity<ErroPadrao> handlerVeiculoIndisponivelException(VeiculoIndisponivelException ex) {
        ErroPadrao erro = new ErroPadrao();
        erro.setCodigoErro(ErroCodigo.VEICULO_INDISPONIVEL.name());
        erro.setDataHora(LocalDateTime.now());
        erro.setMensagem(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(VeiculoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> handlerVeiculoNaoEncontradoException(VeiculoNaoEncontradoException ex) {
        ErroPadrao erro = new ErroPadrao();
        erro.setCodigoErro(ErroCodigo.VEICULO_NAO_ENCONTRADO.name());
        erro.setDataHora(LocalDateTime.now());
        erro.setMensagem(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> handlerClienteNaoEncontradoException(ClienteNaoEncontradoException ex) {
        ErroPadrao erro = new ErroPadrao();
        erro.setCodigoErro(ErroCodigo.CLIENTE_NAO_ENCONTRADO.name());
        erro.setDataHora(LocalDateTime.now());
        erro.setMensagem(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
