package io.github.monthalcantara.springboot2essentials.controller;

import io.github.monthalcantara.springboot2essentials.AnimeRepository;
import io.github.monthalcantara.springboot2essentials.compartilhado.validators.ExisteId;
import io.github.monthalcantara.springboot2essentials.domain.Anime;
import io.github.monthalcantara.springboot2essentials.dto.request.AtualizaAnimeRequest;
import io.github.monthalcantara.springboot2essentials.dto.request.NovoAnimeRequest;
import io.github.monthalcantara.springboot2essentials.dto.response.AnimeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animes")
@Validated
public class AnimeController {

    private final AnimeRepository animeRepository;

    public AnimeController(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @GetMapping
    @Transactional
    public ResponseEntity buscaTodosAnimes() {
        List<Anime> listaAnimes = animeRepository.findAll();
        List<AnimeResponse> animeResponses = listaAnimes.stream().map(AnimeResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(animeResponses);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscaAnime(@Valid @ExisteId(atributo = "id", classe = Anime.class,
            message = "Não existe registro de anime com esse id informado") @PathVariable(value = "id") Long id) {
        Optional<Anime> anime = animeRepository.findById(id);
        Assert.state(anime.isPresent(), "Chegou uma busca de um id que não existe no banco aqui no endpoint de busca anime. Validação do endopint não funcionou");
        return ResponseEntity.ok(new AnimeResponse(anime.get()));
    }

    @GetMapping("/nome/{nome}")
    @Transactional
    public ResponseEntity buscaAnimePorNome(@PathVariable("nome") String nome) {
        List<Anime> resultList = animeRepository.findByNome(nome);
        List<AnimeResponse> listaResponse = resultList.stream().map(AnimeResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaResponse);
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvaAnime(@Valid @RequestBody NovoAnimeRequest novoAnimeRequest, UriComponentsBuilder builder) {

        Anime anime = novoAnimeRequest.toModel();
        anime = animeRepository.save(anime);
        //   URI uri = builder.path("/animes/{id}").buildAndExpand(anime.getId()).toUri();
        return ResponseEntity.created(null).body(new AnimeResponse(anime));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizaAnime(@Valid @RequestBody AtualizaAnimeRequest atualizaAnimeRequest) {
        Optional<Anime> anime = animeRepository.findById(atualizaAnimeRequest.getId());
        Assert.state(anime.isPresent(),"Chegou a solicitação de atualização de um id que não existe no banco aqui no endpoint de busca anime. Validação do endopint não funcionou");
        anime.get().setNome(atualizaAnimeRequest.getNome());
        animeRepository.save(anime.get());
        return ResponseEntity.ok(new AnimeResponse(anime.get()));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletaAnime(@PathVariable("id") @ExisteId(classe = Anime.class, atributo = "id",
            message = "Não existe registro de anime com esse id informado") Long id) {
        Optional<Anime> optionalAnime = animeRepository.findById(id);
        Assert.state(optionalAnime.isPresent(), "Chegou uma busca de um id que não existe no banco aqui no endpoint de deleta anime. Validação do endopint não funcionou");
        Anime anime = optionalAnime.get();
        animeRepository.delete(anime);
    }


}
