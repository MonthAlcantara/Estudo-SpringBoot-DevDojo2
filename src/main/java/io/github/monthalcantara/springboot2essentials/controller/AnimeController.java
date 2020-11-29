package io.github.monthalcantara.springboot2essentials.controller;

import io.github.monthalcantara.springboot2essentials.compartilhado.validators.ExisteId;
import io.github.monthalcantara.springboot2essentials.domain.Anime;
import io.github.monthalcantara.springboot2essentials.dto.request.NovoAnimeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/animes")
@Validated
public class AnimeController {

    private final EntityManager manager;

    public AnimeController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity buscaAnimes(@Valid @RequestBody NovoAnimeRequest animeRequest, UriComponentsBuilder builder) {

        Anime anime = animeRequest.toModel();
        manager.persist(anime);
        URI uri = builder.path("/animes/{id}").buildAndExpand(anime.getId()).toUri();
        return ResponseEntity.created(uri).body(new AnimeRespose(anime));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscaAnime(@Valid @ExisteId(atributo = "id", classe = Anime.class,
            message = "Não existe registro de anime com esse id informado") @PathVariable(value = "id") Long id) {
        Anime anime = manager.find(Anime.class, id);
        Assert.state(anime != null, "Chegou uma busca de um id que não existe no banco aqui no endpoint de busca anime. Validação do endopint não funcionou");
        return ResponseEntity.ok(new AnimeRespose(anime));
    }
}
