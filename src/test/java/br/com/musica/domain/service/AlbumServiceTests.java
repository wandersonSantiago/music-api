package br.com.musica.domain.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.musica.api.dto.input.AlbumInputDTO;
import br.com.musica.api.dto.mappers.AlbumMapper;
import br.com.musica.api.dto.output.AlbumOutputDTO;
import br.com.musica.domain.models.Album;
import br.com.musica.domain.models.Artista;
import br.com.musica.domain.repositories.AlbumRepository;
import br.com.musica.domain.repositories.ArtistaRepository;

@SpringBootTest
public class AlbumServiceTests {
	
    @Autowired
    private AlbumService albumService;

    @MockBean
    private AlbumRepository albumRepository;

    @MockBean
    private ArtistaRepository artistaRepository;

    private final AlbumMapper albumMapper = Mappers.getMapper(AlbumMapper.class);

    @Test
    public void testSaveAlbum() {
        AlbumInputDTO albumInputDTO = new AlbumInputDTO();
        albumInputDTO.setTitulo("Album Teste");
        albumInputDTO.setAnoLancamento(LocalDate.now());
        albumInputDTO.setImagemCapa("http://www.album.com/imagem.jpg");
        albumInputDTO.setArtistaId(1L);

        Artista artista = new Artista();
        artista.setId(1L);

        Album album = albumMapper.toEntity(albumInputDTO);
        album.setArtista(artista);

        Mockito.when(artistaRepository.existsById(1L)).thenReturn(true);
        Mockito.when(albumRepository.save(album)).thenReturn(album);

        AlbumOutputDTO savedAlbum = albumService.save(albumInputDTO);

        assertEquals(album.getTitulo(), savedAlbum.getTitulo());
        assertEquals(album.getAnoLancamento(), savedAlbum.getAnoLancamento());
        assertEquals(album.getImagemCapa(), savedAlbum.getImagemCapa());
        assertEquals(album.getArtista().getId(), savedAlbum.getArtista().getId());
    }

    @Test
    public void testFindAlbumById() {
        Album album = new Album();
        album.setId(1L);
        album.setTitulo("Album Teste");

        Mockito.when(albumRepository.findById(1L)).thenReturn(Optional.of(album));

        Optional<AlbumOutputDTO> foundAlbum = albumService.findById(1L);

        assertTrue(foundAlbum.isPresent());
        assertEquals(album.getTitulo(), foundAlbum.get().getTitulo());
    }

    @Test
    public void testFindAllAlbuns() {
        Album album = new Album();
        album.setId(1L);
        album.setTitulo("Album Teste");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Album> albuns = new PageImpl<>(Collections.singletonList(album), pageable, 1);

        Mockito.when(albumRepository.findAll(pageable)).thenReturn(albuns);

        Page<AlbumOutputDTO> foundAlbuns = albumService.findAll(pageable);

        assertEquals(1, foundAlbuns.getTotalElements());
        assertEquals(album.getTitulo(), foundAlbuns.getContent().get(0).getTitulo());
    }

    @Test
    public void testDeleteAlbumById() {
        Album album = new Album();
        album.setId(1L);

        Mockito.when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
        Mockito.doNothing().when(albumRepository).deleteById(1L);

        albumService.deleteById(1L);

        Mockito.verify(albumRepository, Mockito.times(1)).deleteById(1L);
    }
}
