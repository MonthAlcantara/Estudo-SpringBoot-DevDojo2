package io.github.monthalcantara.springboot2essentials.compartilhado.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErroApi {

    private String title;
    private int Status;
    private String details;
    private LocalDateTime timestamp;
    private List<String> erros = new ArrayList<>();

    public ErroApi(String title, int status, String details, List<String> erros, LocalDateTime timestamp) {
        this.title = title;
        Status = status;
        this.details = details;
        this.timestamp = timestamp;
        this.erros = erros;
    }

    public List<String> getErros() {
        return erros;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return Status;
    }

    public String getDetails() {
        return details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
