package br.com.musica.api.dto.mappers;

import org.mapstruct.Mapper;

import br.com.musica.api.dto.input.MusicaInputDTO;
import br.com.musica.api.dto.output.MusicaOutputDTO;
import br.com.musica.domain.models.Musica;

@Mapper(componentModel = "spring", uses = AlbumMapper.class)
public interface MusicaMapper {

	Musica toEntity(MusicaInputDTO dto);

	MusicaOutputDTO toDTO(Musica entity);
}
