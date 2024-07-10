package br.com.musica.domain.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

import br.com.musica.core.validators.DurationConstraint;

@Getter
@Setter
@Entity
public class Musica {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String titulo;

    @NotNull
    @Positive
    private Integer numeroFaixa;

    @NotNull
    @DurationConstraint
    private Duration duracao;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

}
