package io.github.monthalcantara.springboot2essentials.controller;

import io.github.monthalcantara.springboot2essentials.compartilhado.validators.ExisteId;
import io.github.monthalcantara.springboot2essentials.domain.Anime;
import io.github.monthalcantara.springboot2essentials.dto.request.AtualizaAnimeRequest;
import io.github.monthalcantara.springboot2essentials.dto.request.NovoAnimeRequest;
import io.github.monthalcantara.springboot2essentials.dto.response.AnimeRespose;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animes")
@Validated
public class AnimeController {

    private final EntityManager manager;

    public AnimeController(EntityManager manager) {
        this.manager = manager;
    }

    @GetMapping
    @Transactional
    public ResponseEntity buscaTodosAnimes() {
        List<Anime> listaAnimes = manager.createQuery("Select a from Anime a").getResultList();
        List<AnimeRespose> animeResposes = listaAnimes.stream().map(AnimeRespose::new).collect(Collectors.toList());
        return ResponseEntity.ok(animeResposes);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscaAnime(@Valid @ExisteId(atributo = "id", classe = Anime.class,
            message = "Não existe registro de anime com esse id informado") @PathVariable(value = "id") Long id) {
        Anime anime = manager.find(Anime.class, id);
        Assert.state(anime != null, "Chegou uma busca de um id que não existe no banco aqui no endpoint de busca anime. Validação do endopint não funcionou");
        return ResponseEntity.ok(new AnimeRespose(anime));
    }

    @PostMapping
    @Transactional
    public ResponseEntity buscaAnimes(@Valid @RequestBody NovoAnimeRequest novoAnimeRequest, UriComponentsBuilder builder) {

        Anime anime = novoAnimeRequest.toModel();
        manager.persist(anime);
        URI uri = builder.path("/animes/{id}").buildAndExpand(anime.getId()).toUri();
        return ResponseEntity.created(uri).body(new AnimeRespose(anime));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizaAnime(@Valid @RequestBody AtualizaAnimeRequest atualizaAnimeRequest) {
        Anime anime = manager.find(Anime.class, atualizaAnimeRequest.getId());
        anime.setNome(atualizaAnimeRequest.getNome());
        manager.merge(anime);
        return ResponseEntity.ok(new AnimeRespose(anime));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletaAnime(@PathVariable("id") @ExisteId(classe = Anime.class, atributo = "id",
            message = "Não existe registro de anime com esse id informado") Long id) {
        Anime anime = manager.find(Anime.class, id);
        Assert.state(Optional.ofNullable(anime).isPresent(), "Chegou uma busca de um id que não existe no banco aqui no endpoint de deleta anime. Validação do endopint não funcionou");
        manager.remove(anime);
    }

}
