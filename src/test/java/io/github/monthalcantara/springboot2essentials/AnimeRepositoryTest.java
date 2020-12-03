package io.github.monthalcantara.springboot2essentials;

import io.github.monthalcantara.springboot2essentials.domain.Anime;
import io.github.monthalcantara.springboot2essentials.repository.AnimeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Testes para o Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository repository;

    @Test
    @DisplayName("Deveria salvar Anime no banco")
    void saveTest_Sucessful() {
        Anime anime = this.repository.save(criaAnime());
        Assertions.assertNotNull(anime);
        Assertions.assertNotNull(anime.getId());
        Assertions.assertTrue(anime.getNome().equals("Pokemon"));
    }

    /*
        Forma 01 de fazer validação de lançamento de exception
        Assertions do pacote org.assertj.core.api
     */
    @Test
    @DisplayName("Deveria dar erro ao tentar salvar anime com nome em branco (Forma 01)")
    void saveTest1_ThrowsConstraintViolationException() {
        //Certifique-se se é lançado por this.repository.save(new Anime("") uma instancia de ConstraintViolationException.class
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> this.repository.save(new Anime("")))
                .isInstanceOf(ConstraintViolationException.class); // Constraint do pacote Javax.validation
    }

    /*
        Forma 02 de fazer validação de lançamento de exception
        Assertions do pacote org.assertj.core.api
     */
    @Test
    @DisplayName("Deveria dar erro ao tentar salvar anime com nome em branco (Forma 02)")
    void saveTest2_ThrowsConstraintViolationException() {
        // Constraint do pacote Javax.validation
        //Certifique-se que uma exception do tipo ConstraintViolationException é lançada por this.repository.save(new Anime(""))
        org.assertj.core.api.Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(() -> this.repository.save(new Anime("")));
    }

    /*
    Forma 03 de fazer validação de lançamento de exception
    Assertions do pacote org.junit
 */
    @Test
    @DisplayName("Deveria dar erro ao tentar salvar anime com nome em branco (Forma 03)")
    void saveTest3_ThrowsConstraintViolationException() {
        // Constraint do pacote Javax.validation
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.repository.save(new Anime("")));
    }

    @Test
    @DisplayName("Deveria atualizar Anime pelo Id no banco")
    void save_updateTest_Sucessfull() {
        Anime anime = this.repository.save(criaAnime());
        anime.setNome("Digimon");
        Anime animeAtualizado = this.repository.save(anime);
        Assertions.assertNotNull(animeAtualizado);
        Assertions.assertTrue(anime.getId().equals(animeAtualizado.getId()));
        Assertions.assertTrue(animeAtualizado.getNome().equals("Digimon"));
    }

    @Test
    @DisplayName("Deveria buscar um anime pelo id no banco")
    void findByIdTest_Sucessful() {
        Anime animeSalvo = this.repository.save(criaAnime());
        Optional<Anime> optionalAnime = this.repository.findById(animeSalvo.getId());
        Assertions.assertTrue(optionalAnime.isPresent());
        Assertions.assertNotNull(optionalAnime.get().getId());
        Assertions.assertTrue(optionalAnime.get().getId().equals(animeSalvo.getId()));
    }

    @Test
    @DisplayName("Deveria buscar um anime pelo nome no banco")
    void findByNomeTest_Sucessful() {
        this.repository.save(criaAnime());
        List<Anime> pokemon = this.repository.findByNome("Pokemon");
        Assertions.assertFalse(pokemon.isEmpty());
    }

    @Test
    @DisplayName("Deveria deletar um anime no banco pelo id")
    void delete_Sucessful() {
        Anime animeSalvo = this.repository.save(criaAnime());
        Assertions.assertNotNull(animeSalvo);
        this.repository.delete(animeSalvo);
        Optional<Anime> optionalAnime = this.repository.findById(1L);
        Assertions.assertTrue(optionalAnime.isEmpty());
    }

    private Anime criaAnime() {
        return new Anime("Pokemon");
    }
}