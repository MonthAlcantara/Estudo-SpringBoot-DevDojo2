package io.github.monthalcantara.springboot2essentials.controller;

import io.github.monthalcantara.springboot2essentials.repository.AnimeRepository;
import io.github.monthalcantara.springboot2essentials.util.AnimeCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

//@WebMvcTest
@ExtendWith(SpringExtension.class)
//@SpringBootTest
@DisplayName("Teste controller de Animes")
public class AnimeControllerTest {


    @InjectMocks
    private AnimeController animeController;

    private AnimeRepository repository = Mockito.mock(AnimeRepository.class);


    @Test
    @DisplayName("Deveria buscar todos os animes")
    void buscaTodosAnimes(){
        Mockito.when(repository.findAll()).thenReturn(List.of(AnimeCreator.createAnimeSalvo()));
        ResponseEntity responseEntity = animeController.buscaTodosAnimes();

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(responseEntity.getBody());
    }

}
