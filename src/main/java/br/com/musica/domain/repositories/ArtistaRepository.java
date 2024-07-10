package br.com.musica.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musica.domain.models.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {}
