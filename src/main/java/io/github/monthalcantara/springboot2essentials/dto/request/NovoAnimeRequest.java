package io.github.monthalcantara.springboot2essentials.dto.request;

import io.github.monthalcantara.springboot2essentials.compartilhado.validators.ValorUnico;
import io.github.monthalcantara.springboot2essentials.domain.Anime;

import javax.validation.constraints.NotBlank;

public class NovoAnimeRequest {

    @NotBlank
    @ValorUnico(atributo = "nome", classe = Anime.class, message = "Ja existe um Anime cadastrado com esse nome")
    private String nome;

    @Deprecated
    private NovoAnimeRequest() {
    }

    public NovoAnimeRequest(String nome) {
        this.nome = nome;
    }

    public Anime toModel() {
        return new Anime(this.nome);
    }

    public String getNome() {
        return nome;
    }
}
