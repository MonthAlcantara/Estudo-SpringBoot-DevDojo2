package io.github.monthalcantara.springboot2essentials.util;

import io.github.monthalcantara.springboot2essentials.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeParaSerSalvo(){
        return new Anime("Dragon Ball z");
    }

    public static Anime createAnimeSalvo(){
        Anime anime = new Anime("Dragon Ball z");
        anime.setId(1L);
        return anime;
    }

    public static Anime createAnimeAtualizado(){
        Anime anime = new Anime("Dragon Ball");
        anime.setId(1L);
        return anime;
    }
}
