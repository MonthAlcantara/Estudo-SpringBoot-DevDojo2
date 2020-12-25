package io.github.monthalcantara.springboot2essentials.repository;

import io.github.monthalcantara.springboot2essentials.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {

    List<Anime> findByNome(String nome);
}
