package io.github.monthalcantara.springboot2essentials.dto.response;

import io.github.monthalcantara.springboot2essentials.domain.Anime;

public class AnimeResponse {

    private String nome;

    @Deprecated
    private AnimeResponse() {
    }

    public AnimeResponse(Anime anime) {
        this.nome = anime.getNome();
    }

    public String getNome() {
        return nome;
    }
}
