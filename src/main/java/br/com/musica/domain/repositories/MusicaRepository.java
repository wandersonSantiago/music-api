package br.com.musica.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musica.domain.models.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
	
	List<Musica> findByAlbumArtistaId(Long artistaId);

	List<Musica> findByAlbumIdOrderByNumeroFaixa(Long albumId);

	List<Musica> findByAlbumIdOrderByTitulo(Long albumId);
}
