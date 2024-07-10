package br.com.musica.api.resources.v1;


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

import br.com.musica.api.dto.input.ArtistaInputDTO;
import br.com.musica.api.dto.output.ArtistaOutputDTO;
import br.com.musica.domain.service.ArtistaService;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaResource {
	@Autowired
	private ArtistaService artistaService;

	@PostMapping
	public ResponseEntity<ArtistaOutputDTO> create(@RequestBody ArtistaInputDTO artistaInputDTO) {
		ArtistaOutputDTO savedArtista = artistaService.save(artistaInputDTO);
		return new ResponseEntity<>(savedArtista, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArtistaOutputDTO> getById(@PathVariable Long id) {
		Optional<ArtistaOutputDTO> artista = artistaService.findById(id);
		return artista.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public Page<ArtistaOutputDTO> getAll(Pageable pageable) {
		return artistaService.findAll(pageable);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ArtistaOutputDTO> update(@PathVariable Long id,
			@RequestBody ArtistaInputDTO artistaInputDTO) {
		if (!artistaService.findById(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ArtistaOutputDTO updatedArtista = artistaService.save(artistaInputDTO);
		return new ResponseEntity<>(updatedArtista, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!artistaService.findById(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		artistaService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
