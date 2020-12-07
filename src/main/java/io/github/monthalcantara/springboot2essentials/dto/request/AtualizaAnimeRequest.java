package io.github.monthalcantara.springboot2essentials.dto.request;

import io.github.monthalcantara.springboot2essentials.compartilhado.validators.ExisteId;
import io.github.monthalcantara.springboot2essentials.domain.Anime;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class AtualizaAnimeRequest {

    @ExisteId(classe = Anime.class, atributo = "id", message = "Não foi encontrado Anime com este id")
    private Long id;

    @NotBlank
    private String nome;

    @Deprecated
    private AtualizaAnimeRequest() {
    }

    public AtualizaAnimeRequest(Long id, @NotBlank String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Anime toModel() {
        return new Anime(this.nome);
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

}
