package br.com.musica.domain.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
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

import br.com.musica.api.dto.input.MusicaInputDTO;
import br.com.musica.api.dto.mappers.MusicaMapper;
import br.com.musica.api.dto.output.MusicaOutputDTO;
import br.com.musica.domain.models.Album;
import br.com.musica.domain.models.Musica;
import br.com.musica.domain.repositories.AlbumRepository;
import br.com.musica.domain.repositories.MusicaRepository;

@SpringBootTest
public class MusicaServiceTests {
	
    @Autowired
    private MusicaService musicaService;

    @MockBean
    private MusicaRepository musicaRepository;

    @MockBean
    private AlbumRepository albumRepository;

    private final MusicaMapper musicaMapper = Mappers.getMapper(MusicaMapper.class);

    @Test
    public void testSaveMusica() {
        MusicaInputDTO musicaInputDTO = new MusicaInputDTO();
        musicaInputDTO.setTitulo("Musica Teste");
        musicaInputDTO.setNumeroFaixa(1);
        musicaInputDTO.setDuracao(Duration.ofMinutes(3).plusSeconds(30));
        musicaInputDTO.setAlbumId(1L);

        Album album = new Album();
        album.setId(1L);

        Musica musica = musicaMapper.toEntity(musicaInputDTO);
        musica.setAlbum(album);

        Mockito.when(albumRepository.existsById(1L)).thenReturn(true);
        Mockito.when(musicaRepository.save(musica)).thenReturn(musica);

        MusicaOutputDTO savedMusica = musicaService.save(musicaInputDTO);

        assertEquals(musica.getTitulo(), savedMusica.getTitulo());
        assertEquals(musica.getNumeroFaixa(), savedMusica.getNumeroFaixa());
        assertEquals(musica.getDuracao(), savedMusica.getDuracao());
        assertEquals(musica.getAlbum().getId(), savedMusica.getAlbum().getId());
    }

    @Test
    public void testFindMusicaById() {
        Musica musica = new Musica();
        musica.setId(1L);
        musica.setTitulo("Musica Teste");

        Mockito.when(musicaRepository.findById(1L)).thenReturn(Optional.of(musica));

        Optional<MusicaOutputDTO> foundMusica = musicaService.findById(1L);

        assertTrue(foundMusica.isPresent());
        assertEquals(musica.getTitulo(), foundMusica.get().getTitulo());
    }

    @Test
    public void testFindAllMusicas() {
        Musica musica = new Musica();
        musica.setId(1L);
        musica.setTitulo("Musica Teste");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Musica> musicas = new PageImpl<>(Collections.singletonList(musica), pageable, 1);

        Mockito.when(musicaRepository.findAll(pageable)).thenReturn(musicas);

        Page<MusicaOutputDTO> foundMusicas = musicaService.findAll(pageable);

        assertEquals(1, foundMusicas.getTotalElements());
        assertEquals(musica.getTitulo(), foundMusicas.getContent().get(0).getTitulo());
    }

    @Test
    public void testDeleteMusicaById() {
        Musica musica = new Musica();
        musica.setId(1L);

        Mockito.when(musicaRepository.findById(1L)).thenReturn(Optional.of(musica));
        Mockito.doNothing().when(musicaRepository).deleteById(1L);

        musicaService.deleteById(1L);

        Mockito.verify(musicaRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testFindByArtistaId() {
        Musica musica = new Musica();
        musica.setId(1L);
        musica.setTitulo("Musica Teste");

        Mockito.when(musicaRepository.findByAlbumArtistaId(1L)).thenReturn(Collections.singletonList(musica));

        List<MusicaOutputDTO> foundMusicas = musicaService.findByArtistaId(1L);

        assertEquals(1, foundMusicas.size());
        assertEquals(musica.getTitulo(), foundMusicas.get(0).getTitulo());
    }

    @Test
    public void testFindByAlbumIdOrderByNumeroFaixa() {
        Musica musica = new Musica();
        musica.setId(1L);
        musica.setTitulo("Musica Teste");

        Mockito.when(musicaRepository.findByAlbumIdOrderByNumeroFaixa(1L)).thenReturn(Collections.singletonList(musica));

        List<MusicaOutputDTO> foundMusicas = musicaService.findByAlbumIdOrderByNumeroFaixa(1L);

        assertEquals(1, foundMusicas.size());
        assertEquals(musica.getTitulo(), foundMusicas.get(0).getTitulo());
    }

    @Test
    public void testFindByAlbumIdOrderByTitulo() {
        Musica musica = new Musica();
        musica.setId(1L);
        musica.setTitulo("Musica Teste");

        Mockito.when(musicaRepository.findByAlbumIdOrderByTitulo(1L)).thenReturn(Collections.singletonList(musica));

        List<MusicaOutputDTO> foundMusicas = musicaService.findByAlbumIdOrderByTitulo(1L);

        assertEquals(1, foundMusicas.size());
        assertEquals(musica.getTitulo(), foundMusicas.get(0).getTitulo());
    }
}

