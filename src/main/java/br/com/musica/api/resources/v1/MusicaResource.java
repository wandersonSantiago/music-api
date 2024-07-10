package br.com.musica.api.resources.v1;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musica.api.dto.input.MusicaInputDTO;
import br.com.musica.api.dto.output.MusicaOutputDTO;
import br.com.musica.domain.service.MusicaService;

@RestController
@RequestMapping("/api/musicas")
public class MusicaResource {
	
	@Autowired
	private MusicaService musicaService;

	@PostMapping
	public ResponseEntity<MusicaOutputDTO> create(@RequestBody MusicaInputDTO musicaInputDTO) {
		MusicaOutputDTO savedMusica = musicaService.save(musicaInputDTO);
		return new ResponseEntity<>(savedMusica, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MusicaOutputDTO> getById(@PathVariable Long id) {
		Optional<MusicaOutputDTO> musica = musicaService.findById(id);
		return musica.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public Page<MusicaOutputDTO> getAll(Pageable pageable) {
		return musicaService.findAll(pageable);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MusicaOutputDTO> update(@PathVariable Long id, @RequestBody MusicaInputDTO musicaInputDTO) {
		if (!musicaService.findById(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		MusicaOutputDTO updatedMusica = musicaService.save(musicaInputDTO);
		return new ResponseEntity<>(updatedMusica, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!musicaService.findById(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		musicaService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/artista/{artistaId}")
	public ResponseEntity<List<MusicaOutputDTO>> getByArtistaId(@PathVariable Long artistaId) {
		List<MusicaOutputDTO> musicas = musicaService.findByArtistaId(artistaId);
		return new ResponseEntity<>(musicas, HttpStatus.OK);
	}

	@GetMapping("/album/{albumId}/numerofaixa")
	public ResponseEntity<List<MusicaOutputDTO>> getByAlbumIdOrderByNumeroFaixa(@PathVariable Long albumId) {
		List<MusicaOutputDTO> musicas = musicaService.findByAlbumIdOrderByNumeroFaixa(albumId);
		return new ResponseEntity<>(musicas, HttpStatus.OK);
	}

	@GetMapping("/album/{albumId}/titulo")
	public ResponseEntity<List<MusicaOutputDTO>> getByAlbumIdOrderByTitulo(@PathVariable Long albumId) {
		List<MusicaOutputDTO> musicas = musicaService.findByAlbumIdOrderByTitulo(albumId);
		return new ResponseEntity<>(musicas, HttpStatus.OK);
	}
}
