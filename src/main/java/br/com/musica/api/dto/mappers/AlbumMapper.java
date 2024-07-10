package br.com.musica.api.dto.mappers;

import org.mapstruct.Mapper;

import br.com.musica.api.dto.input.AlbumInputDTO;
import br.com.musica.api.dto.output.AlbumOutputDTO;
import br.com.musica.domain.models.Album;

@Mapper(componentModel = "spring", uses = ArtistaMapper.class)
public interface AlbumMapper {

	Album toEntity(AlbumInputDTO dto);

	AlbumOutputDTO toDTO(Album entity);
}
