package io.github.monthalcantara.springboot2essentials.dto.request;

import io.github.monthalcantara.springboot2essentials.domain.Anime;

import javax.validation.constraints.NotBlank;

public class NovoAnimeRequest {

    @NotBlank
    private String nome;

    @Deprecated
    private NovoAnimeRequest() {
    }

    public NovoAnimeRequest(String nome) {
        this.nome = nome;
    }

    public Anime toModel(){
        return new Anime(this.nome);
    }
    public String getNome() {
        return nome;
    }
}
