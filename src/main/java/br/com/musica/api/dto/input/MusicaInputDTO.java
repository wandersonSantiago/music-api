package br.com.musica.api.dto.input;

import java.time.Duration;

import br.com.musica.core.validators.DurationConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicaInputDTO {
	
	@NotNull
	@Size(min = 1, max = 100)
	private String titulo;

	@NotNull
	@Positive
	private Integer numeroFaixa;

	@NotNull
	@DurationConstraint
	private Duration duracao;

	@NotNull
	private Long albumId;

}
