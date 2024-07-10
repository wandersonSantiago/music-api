package br.com.musica.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import br.com.musica.api.dto.input.ArtistaInputDTO;
import br.com.musica.api.dto.mappers.ArtistaMapper;
import br.com.musica.api.dto.output.ArtistaOutputDTO;
import br.com.musica.domain.models.Artista;
import br.com.musica.domain.repositories.ArtistaRepository;

@SpringBootTest
public class ArtistaServiceTests {
	
    @Autowired
    private ArtistaService artistaService;

    @MockBean
    private ArtistaRepository artistaRepository;

    private final ArtistaMapper artistaMapper = Mappers.getMapper(ArtistaMapper.class);

    @Test
    public void testSaveArtista() {
        ArtistaInputDTO artistaInputDTO = new ArtistaInputDTO();
        artistaInputDTO.setNome("Artista Teste");
        artistaInputDTO.setNacionalidade("Brasileiro");
        artistaInputDTO.setEnderecoSite("http://www.artista.com");
        artistaInputDTO.setImagemPerfil("http://www.artista.com/imagem.jpg");

        Artista artista = artistaMapper.toEntity(artistaInputDTO);

        Mockito.when(artistaRepository.save(artista)).thenReturn(artista);

        ArtistaOutputDTO savedArtista = artistaService.save(artistaInputDTO);

        assertEquals(artista.getNome(), savedArtista.getNome());
        assertEquals(artista.getNacionalidade(), savedArtista.getNacionalidade());
        assertEquals(artista.getEnderecoSite(), savedArtista.getEnderecoSite());
        assertEquals(artista.getImagemPerfil(), savedArtista.getImagemPerfil());
    }

    @Test
    public void testFindArtistaById() {
        Artista artista = new Artista();
        artista.setId(1L);
        artista.setNome("Artista Teste");

        Mockito.when(artistaRepository.findById(1L)).thenReturn(Optional.of(artista));

        Optional<ArtistaOutputDTO> foundArtista = artistaService.findById(1L);

        assertTrue(foundArtista.isPresent());
        assertEquals(artista.getNome(), foundArtista.get().getNome());
    }

    @Test
    public void testFindAllArtistas() {
        Artista artista = new Artista();
        artista.setId(1L);
        artista.setNome("Artista Teste");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Artista> artistas = new PageImpl<>(Collections.singletonList(artista), pageable, 1);

        Mockito.when(artistaRepository.findAll(pageable)).thenReturn(artistas);

        Page<ArtistaOutputDTO> foundArtistas = artistaService.findAll(pageable);

        assertEquals(1, foundArtistas.getTotalElements());
        assertEquals(artista.getNome(), foundArtistas.getContent().get(0).getNome());
    }

    @Test
    public void testDeleteArtistaById() {
        Artista artista = new Artista();
        artista.setId(1L);

        Mockito.when(artistaRepository.findById(1L)).thenReturn(Optional.of(artista));
        Mockito.doNothing().when(artistaRepository).deleteById(1L);

        artistaService.deleteById(1L);

        Mockito.verify(artistaRepository, Mockito.times(1)).deleteById(1L);
    }
}
