package io.github.monthalcantara.springboot2essentials.util;

import io.github.monthalcantara.springboot2essentials.domain.Anime;

public class AnimeCreator {

   private Anime anime;

    public static Anime createAnimeParaSerSalvo(){
        return new Anime("Dragon Ball z");
    }

    public AnimeCreator() {
        this.anime = new Anime();
    }

    public AnimeCreator comNome(String nome){
        this.anime.setNome(nome);
        return this;
    }

    public AnimeCreator comId(Integer id){
        anime.setId(id);
        return this;
    }

    public Anime build(){
        return this.anime;
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
