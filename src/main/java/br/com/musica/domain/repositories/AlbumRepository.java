package br.com.musica.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musica.domain.models.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
