package br.com.musica.api.dto.output;

import java.time.Duration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicaOutputDTO {
	
    private Long id;
    private String titulo;
    private Integer numeroFaixa;
    private Duration duracao;
    private AlbumOutputDTO album;

}
