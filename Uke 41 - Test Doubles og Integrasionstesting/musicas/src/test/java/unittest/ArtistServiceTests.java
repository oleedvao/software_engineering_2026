package unittest;

import core.domain.Artist;
import core.domain.Song;
import core.dto.CreateArtistRequest;
import core.dto.GetArtistSongsWithLengthRequest;
import core.dto.GetArtistSongsWithLengthResult;
import core.port.ArtistRepositoryPort;
import core.service.ArtistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTests {


    // Mocker ArtistRepositoryPort slik at vi kan definere hvordan denne skal fungere i testene våre
    @Mock
    ArtistRepositoryPort artistRepositoryMock;

    /*
    Dette er et eksempel hvor det er fornuftig å mocke for å forsikre oss om at vi kan teste filtreringslogikken i
    getArtistSongsWithLength(). Spesifikt fordi filtreringslogikken er avhengig av hva som returneres av
    "ArtistRepositoriet" (implementasjon av ArtistRepositoryPort) som er i bruk. Vi lager altså en mock av
    ArtistReositoryPort og forfalsker resultatet til noe forutsigbart når enheten kaller .getArtistSongs() - Her en
    liste med sanger som har varierte lengder, slik at vi får tested filtreringslogikken.
     */
    @Test
    public void getArtistSongsWithLength_ArtistSongsLongerOrEqualsToLengthAreReturned() throws Exception {

        // Arrange
        //ArtistRepositoryPort artistRepositoryMock = Mockito.mock(ArtistRepositoryPort.class);
        ArtistService artistService = new ArtistService(artistRepositoryMock);

        ArrayList<Song> stubbedSongs = new ArrayList<>();
        stubbedSongs.add(new Song("Song 1", 300, 5, 10));
        stubbedSongs.add(new Song("Song 2", 150, 5, 10));
        stubbedSongs.add(new Song("Song 3", 200, 5, 10));
        Mockito.when(artistRepositoryMock.getArtistSongs(5)).thenReturn(stubbedSongs);

        int artistId = 5;
        int lengthInSeconds = 200;
        GetArtistSongsWithLengthRequest request = new GetArtistSongsWithLengthRequest(
                artistId, lengthInSeconds
        );

        // Act
        GetArtistSongsWithLengthResult result = artistService.getArtistSongsWithLength(request);

        // Assert
        Assertions.assertEquals(5, result.getArtistId());
        Assertions.assertEquals(200, result.getFilteredLengthInSeconds());
        Assertions.assertEquals(2, result.getSongDTOs().size());
        Assertions.assertEquals("Song 1", result.getSongDTOs().get(0).getTitle());
        Assertions.assertEquals("Song 3", result.getSongDTOs().get(1).getTitle());
    }

    /*
    Ved gyldig request kontrollerer vi at ArtistRepositoriet sin createArtist() blir kalt nøyaktig 1 gang
    internt i enheten ved bruk av Mockito.verify().
     */
    @Test
    public void createArtist_RequestResultsInArtistRepositoryCall() throws Exception{
        ArtistService artistService = new ArtistService(artistRepositoryMock);

        artistService.createArtist(new CreateArtistRequest("Test"));

        Mockito.verify(artistRepositoryMock, Mockito.times(1))
                .createArtist(Mockito.any(Artist.class));
    }

    /*
    Dersom requesten er null kontrollerer vi at det kastes en NullPointerException.
     */
    @Test
    public void createArtist_NullRequestThrowsNullPointerException() throws Exception{
        ArtistService artistService = new ArtistService(artistRepositoryMock);

        Assertions.assertThrows(NullPointerException.class, () -> {
            artistService.createArtist(null);
        });
    }



    /* NB!
    Denne testen er fullstendig UNØDVENDIG ettersom den ikke gir noen mer innsikt enn hvis vi leser ArtistService sin
    getNumArtists() slik den er definert. Med andre ord, har testen en meget lav "test-verdi".
    Vi kan altså helt fint fjerne/utelate denne testen, men jeg lar den stå bare for å illustrere hvordan man IKKE
    bør bruke mocking.
     */
    @Test
    public void getNumArtists() {
        ArtistService artistService = new ArtistService(artistRepositoryMock);

        Mockito.when(artistService.getNumArtists()).thenReturn(5);

        int result = artistService.getNumArtists();

        Assertions.assertEquals(5, result);
        Mockito.verify(artistRepositoryMock, Mockito.times(1)).getNumArtists();
    }

}
