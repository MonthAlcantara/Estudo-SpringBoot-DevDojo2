package io.github.monthalcantara.springboot2essentials.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do anime precisa ser informado")
    @Column(nullable = false,unique = true)
    private String nome;

    @Deprecated
    public Anime() {
    }
    public Anime(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
