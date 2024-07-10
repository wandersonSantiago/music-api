package br.com.musica.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistaOutputDTO {
	
    private Long id;
    private String nome;
    private String nacionalidade;
    private String enderecoSite;
    private String imagemPerfil;

}
