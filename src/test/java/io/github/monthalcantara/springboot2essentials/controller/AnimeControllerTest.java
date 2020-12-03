package io.github.monthalcantara.springboot2essentials.controller;

import io.github.monthalcantara.springboot2essentials.repository.AnimeRepository;
import io.github.monthalcantara.springboot2essentials.domain.Anime;
import io.github.monthalcantara.springboot2essentials.dto.request.NovoAnimeRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@DataJpaTest
@DisplayName("Teste controller de Animes")
public class AnimeControllerTest {

    @Autowired
    private AnimeRepository repository;

    @MockBean
    private UriComponentsBuilder builder;
    private AnimeController animeController = new AnimeController(repository);



    @Test
    @DisplayName("Deveria Salvar novo Anime")
    void salvaAnimeTest(){
     //   Mockito.when(builder.build(Mockito.anyString()).)
        animeController.salvaAnime(criaNovoRequest(), builder);
        Optional<Anime> anime = repository.findById(1L);
        Assertions.assertTrue(anime.isPresent());
    }

    private NovoAnimeRequest criaNovoRequest(){
        return new NovoAnimeRequest("Dbz");
    }

}
