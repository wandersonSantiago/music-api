package br.com.musica.api.dto.mappers;

import org.mapstruct.Mapper;

import br.com.musica.api.dto.input.ArtistaInputDTO;
import br.com.musica.api.dto.output.ArtistaOutputDTO;
import br.com.musica.domain.models.Artista;

@Mapper(componentModel = "spring")
public interface ArtistaMapper {

	Artista toEntity(ArtistaInputDTO dto);

	ArtistaOutputDTO toDTO(Artista entity);
}
