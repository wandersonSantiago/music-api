package br.com.musica.domain.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String titulo;

    @NotNull
    @PastOrPresent
    private LocalDate anoLancamento;

    @NotNull
    @URL
    private String imagemCapa;

    @ManyToOne
    @JoinColumn(name = "artista_id", nullable = false)
    private Artista artista;

}
