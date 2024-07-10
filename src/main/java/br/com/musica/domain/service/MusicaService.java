package br.com.musica.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.musica.api.dto.input.MusicaInputDTO;
import br.com.musica.api.dto.mappers.MusicaMapper;
import br.com.musica.api.dto.output.MusicaOutputDTO;
import br.com.musica.domain.models.Musica;
import br.com.musica.domain.repositories.AlbumRepository;
import br.com.musica.domain.repositories.MusicaRepository;

@Service
public class MusicaService {

	@Autowired
	private MusicaRepository musicaRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private MusicaMapper musicaMapper;

	public MusicaOutputDTO save(MusicaInputDTO musicaInputDTO) {
		Musica musica = musicaMapper.toEntity(musicaInputDTO);
		if (!albumRepository.existsById(musicaInputDTO.getAlbumId())) {
			throw new IllegalArgumentException("Álbum não encontrado");
		}
		Musica savedMusica = musicaRepository.save(musica);
		return musicaMapper.toDTO(savedMusica);
	}

	public Optional<MusicaOutputDTO> findById(Long id) {
		return musicaRepository.findById(id).map(musicaMapper::toDTO);
	}

	public Page<MusicaOutputDTO> findAll(Pageable pageable) {
		return musicaRepository.findAll(pageable).map(musicaMapper::toDTO);
	}

	public void deleteById(Long id) {
		musicaRepository.deleteById(id);
	}

	public List<MusicaOutputDTO> findByArtistaId(Long artistaId) {
		return musicaRepository.findByAlbumArtistaId(artistaId).stream().map(musicaMapper::toDTO).toList();
	}

	public List<MusicaOutputDTO> findByAlbumIdOrderByNumeroFaixa(Long albumId) {
		return musicaRepository.findByAlbumIdOrderByNumeroFaixa(albumId).stream().map(musicaMapper::toDTO).toList();
	}

	public List<MusicaOutputDTO> findByAlbumIdOrderByTitulo(Long albumId) {
		return musicaRepository.findByAlbumIdOrderByTitulo(albumId).stream().map(musicaMapper::toDTO).toList();
	}
}
