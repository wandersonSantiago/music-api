package br.com.musica.api.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Getter
@Setter
public class AlbumInputDTO {
	
    @NotNull
    @Size(min = 2, max = 100)
    private String titulo;

    @NotNull
    @PastOrPresent
    private LocalDate anoLancamento;

    @NotNull
    @URL
    private String imagemCapa;

    @NotNull
    private Long artistaId;

}
