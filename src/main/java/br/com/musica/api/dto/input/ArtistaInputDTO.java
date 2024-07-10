package br.com.musica.api.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class ArtistaInputDTO {
	
    @NotNull
    @Size(min = 2, max = 100)
    private String nome;

    @NotNull
    @Size(min = 2, max = 50)
    private String nacionalidade;

    @NotNull
    @URL
    private String enderecoSite;

    @NotNull
    @URL
    private String imagemPerfil;

}
