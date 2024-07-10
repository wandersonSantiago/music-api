package br.com.musica.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.musica.api.dto.input.ArtistaInputDTO;
import br.com.musica.api.dto.mappers.ArtistaMapper;
import br.com.musica.api.dto.output.ArtistaOutputDTO;
import br.com.musica.domain.models.Artista;
import br.com.musica.domain.repositories.ArtistaRepository;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository;

	@Autowired
	private ArtistaMapper artistaMapper;

	public ArtistaOutputDTO save(ArtistaInputDTO artistaInputDTO) {
		Artista artista = artistaMapper.toEntity(artistaInputDTO);
		Artista savedArtista = artistaRepository.save(artista);
		return artistaMapper.toDTO(savedArtista);
	}

	public Optional<ArtistaOutputDTO> findById(Long id) {
		return artistaRepository.findById(id).map(artistaMapper::toDTO);
	}

	public Page<ArtistaOutputDTO> findAll(Pageable pageable) {
		return artistaRepository.findAll(pageable).map(artistaMapper::toDTO);
	}

	public void deleteById(Long id) {
		artistaRepository.deleteById(id);
	}
}
