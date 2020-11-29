package io.github.monthalcantara.springboot2essentials.compartilhado.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CapturaErrosAplicacaoController {

    private static Logger logger = Logger.getLogger(CapturaErrosAplicacaoController.class);

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroApi handelResponseStatusException(IllegalStateException e) {
        logger.error(e.getMessage());
        return new ErroApi("Desculpa, estamos com alguns problemas internos mas logo logo tudo será normalizado");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroApi handelConstraintViolationException(ConstraintViolationException e) {
        return new ErroApi(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroApi handelMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> listaErros = e.getBindingResult().getAllErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
        return new ErroApi(listaErros);
    }
}
