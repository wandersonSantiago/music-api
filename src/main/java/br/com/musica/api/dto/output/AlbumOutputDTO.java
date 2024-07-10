package br.com.musica.api.dto.output;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumOutputDTO {
	
    private Long id;
    private String titulo;
    private LocalDate anoLancamento;
    private String imagemCapa;
    private ArtistaOutputDTO artista;

}
