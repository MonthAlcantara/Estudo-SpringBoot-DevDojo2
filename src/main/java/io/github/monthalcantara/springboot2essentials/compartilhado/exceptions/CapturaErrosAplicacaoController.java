package io.github.monthalcantara.springboot2essentials.compartilhado.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CapturaErrosAplicacaoController {

    private static Logger logger = Logger.getLogger(CapturaErrosAplicacaoController.class);

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroApi handelResponseStatusException(IllegalStateException e) {
        String title = "Desculpa, estamos com alguns problemas internos mas logo logo tudo será normalizado";

        logger.error(e.getMessage());
        return new ErroApi(title, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getClass().getName(), Arrays.asList(e.getMessage()), LocalDateTime.now());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroApi handelConstraintViolationException(ConstraintViolationException e) {
        String title = "Bad Request, verifique a documentação";
        return new ErroApi(title, HttpStatus.BAD_REQUEST.value(), e.getClass().getName(), Arrays.asList(e.getMessage()), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroApi handelMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String title = "Bad Request, verifique a documentação";
        List<String> listaErros = e.getBindingResult().getAllErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
        return new ErroApi(title, HttpStatus.BAD_REQUEST.value(), e.getClass().getName(), listaErros, LocalDateTime.now());
    }
}
