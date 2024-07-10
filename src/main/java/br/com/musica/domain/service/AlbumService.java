package br.com.musica.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.musica.api.dto.input.AlbumInputDTO;
import br.com.musica.api.dto.mappers.AlbumMapper;
import br.com.musica.api.dto.output.AlbumOutputDTO;
import br.com.musica.domain.models.Album;
import br.com.musica.domain.repositories.AlbumRepository;
import br.com.musica.domain.repositories.ArtistaRepository;

@Service
public class AlbumService {
	
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private AlbumMapper albumMapper;

    public AlbumOutputDTO save(AlbumInputDTO albumInputDTO) {
        Album album = albumMapper.toEntity(albumInputDTO);
        if (!artistaRepository.existsById(albumInputDTO.getArtistaId())) {
            throw new IllegalArgumentException("Artista não encontrado");
        }
        Album savedAlbum = albumRepository.save(album);
        return albumMapper.toDTO(savedAlbum);
    }

    public Optional<AlbumOutputDTO> findById(Long id) {
        return albumRepository.findById(id).map(albumMapper::toDTO);
    }

    public Page<AlbumOutputDTO> findAll(Pageable pageable) {
        return albumRepository.findAll(pageable).map(albumMapper::toDTO);
    }

    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }
}
