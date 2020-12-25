package io.github.monthalcantara.springboot2essentials.repository;

import io.github.monthalcantara.springboot2essentials.domain.Anime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

/*
 * Carrega apenas o contexto de persistencia
 * */
@DataJpaTest
//O nome que aparecerá no display ao rodar a classe de teste
@DisplayName("Teste para o AnimeRepository")
/*
* Por padrão o Spring assume que será utilizado um bando de dados em memória para
* realizar o teste. Se eu não tiver uma dependencia de um bd em memória no meu
* pom.xml o Spring irá acusar erro. Para mudar esse comportamento padrão eu
* posso utilizar a annotation abaixo informando ao sprig para não (NONE) fazer
* a substituição (REPLACE) do meu banco de dados e usar o mesmo que utilizo na minha aplicação
*
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
**/
/*
 * Com essa anotação eu digo que ao rodar o teste eu quero que seja carregado o profile de test
 * Antes dessa anotação eu havia criado o application-test.properties
 *
@ActiveProfiles("test")
* */
public class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository repository;

    @Test
    //O nome que aparecerá no display ao rodar o método de teste
    @DisplayName("Deveria Salvar o anime")
    public void save() {
        Anime animeASerSalvo = createAnime();
        Anime animeSalvo = repository.save(animeASerSalvo);

        Assertions.assertNotNull(animeSalvo);
        Assertions.assertNotNull(animeSalvo.getId());
        Assertions.assertEquals(animeASerSalvo.getNome(), animeSalvo.getNome());
    }

    @Test
    //O nome que aparecerá no display ao rodar o método de teste
    @DisplayName("Deveria Atualizar o anime")
    public void merge() {
        Anime animeASerSalvo = createAnime();
        Anime animeSalvo = repository.save(animeASerSalvo);

        Assertions.assertEquals(animeSalvo.getNome(), animeASerSalvo.getNome());
        Long animeSalvoId = animeSalvo.getId();
        animeSalvo.setNome("Pokemon");
        Anime animeAtualizado = repository.save(animeSalvo);
        Assertions.assertEquals(animeAtualizado.getNome(), "Pokemon");
        Assertions.assertEquals(animeSalvoId, animeAtualizado.getId());

    }

    @Test
    //O nome que aparecerá no display ao rodar o método de teste
    @DisplayName("Deveria Deletar o anime")
    public void delete() {
        Anime animeASerSalvo = createAnime();
        Anime animeSalvo = repository.save(animeASerSalvo);
        Assertions.assertNotNull(animeSalvo);

        repository.delete(animeSalvo);
        Optional<Anime> animeOptional = repository.findById(1L);
        Assertions.assertTrue(animeOptional.isEmpty());
    }

    @Test
    //O nome que aparecerá no display ao rodar o método de teste
    @DisplayName("Deveria buscar o anime por nome")
    public void findByNome() {
        Anime animeASerSalvo = createAnime();
        Anime animeSalvo = repository.save(animeASerSalvo);
        Assertions.assertNotNull(animeSalvo);

        List<Anime> dbz = repository.findByNome("Dbz");
        Assertions.assertFalse(dbz.isEmpty());
        Assertions.assertTrue(dbz.size() == 1);
    }

    @Test
    //O nome que aparecerá no display ao rodar o método de teste
    @DisplayName("Não Deveria buscar um anime que não existe por nome")
    public void findByNomeFail() {
        Anime animeASerSalvo = createAnime();
        Anime animeSalvo = repository.save(animeASerSalvo);
        Assertions.assertNotNull(animeSalvo);

        List<Anime> dbz = repository.findByNome("xxx");
        Assertions.assertTrue(dbz.isEmpty());
        Assertions.assertFalse(dbz.size() > 0);
    }

    @Test
    //O nome que aparecerá no display ao rodar o método de teste
    @DisplayName("Deveria buscar o anime pelo id")
    public void findById() {
        Anime animeASerSalvo = createAnime();
        Anime animeSalvo = repository.save(animeASerSalvo);
        Assertions.assertNotNull(animeSalvo);

        Optional<Anime> animeOptional = repository.findById(animeSalvo.getId());
        Assertions.assertTrue(animeOptional.isPresent());
    }

    @Test
    //O nome que aparecerá no display ao rodar o método de teste
    @DisplayName("Não deveria buscar um anime que não existe no banco pelo id")
    public void findByIdFail() {
        Anime animeASerSalvo = createAnime();
        Anime animeSalvo = repository.save(animeASerSalvo);
        Assertions.assertNotNull(animeSalvo);

        Optional<Anime> animeOptional = repository.findById(50L);
        Assertions.assertFalse(animeOptional.isPresent());
    }

    @Test
    //O nome que aparecerá no display ao rodar o método de teste
    @DisplayName("Deveria lançar um ConstraintViolationException ao tentar salvar anime sem nome")
    public void saveFailConstraintViolationException() {
        Anime anime = new Anime();
//        /*
//        * Verifique se a exception lançadao ao chamar o método ...
//        * */
//        org.assertj.core.api.Assertions.assertThatThrownBy(() -> repository.save(anime))
//        // É uma instancia de...
//       .isInstanceOf(ConstraintViolationException.class);

        RuntimeException exception = Assertions.assertThrows(ConstraintViolationException.class, () -> repository.save(anime));
//        Verifique se a mensagem da exception lançada contem o texto
        Assertions.assertTrue(exception.getMessage().contains("O nome do anime precisa ser informado"));
        /*
         * Verifique se uma exception do tipo...
         * */
//        org.assertj.core.api.Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
//        // É lançada ao chamar o método ....
//                .isThrownBy(()-> repository.save(anime))
//        //e contem a mensagem
//                .withMessageContaining("O nome do anime precisa ser informado");
    }

    private Anime createAnime() {
        Anime anime = new Anime("Dbz");
        return anime;
    }
}
