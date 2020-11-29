package io.github.monthalcantara.springboot2essentials.dto.response;

import io.github.monthalcantara.springboot2essentials.domain.Anime;

public class AnimeRespose {

    private String nome;

    @Deprecated
    private AnimeRespose() {
    }

    public AnimeRespose(Anime anime) {
        this.nome = anime.getNome();
    }

    public String getNome() {
        return nome;
    }
}
