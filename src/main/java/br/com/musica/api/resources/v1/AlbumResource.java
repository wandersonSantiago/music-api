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

import br.com.musica.api.dto.input.AlbumInputDTO;
import br.com.musica.api.dto.output.AlbumOutputDTO;
import br.com.musica.domain.service.AlbumService;

@RestController
@RequestMapping("/api/albuns")
public class AlbumResource {
	@Autowired
	private AlbumService albumService;

	@PostMapping
	public ResponseEntity<AlbumOutputDTO> create(@RequestBody AlbumInputDTO albumInputDTO) {
		AlbumOutputDTO savedAlbum = albumService.save(albumInputDTO);
		return new ResponseEntity<>(savedAlbum, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlbumOutputDTO> getById(@PathVariable Long id) {
		Optional<AlbumOutputDTO> album = albumService.findById(id);
		return album.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public Page<AlbumOutputDTO> getAll(Pageable pageable) {
		return albumService.findAll(pageable);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AlbumOutputDTO> update(@PathVariable Long id, @RequestBody AlbumInputDTO albumInputDTO) {
		if (!albumService.findById(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		AlbumOutputDTO updatedAlbum = albumService.save(albumInputDTO);
		return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!albumService.findById(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		albumService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
