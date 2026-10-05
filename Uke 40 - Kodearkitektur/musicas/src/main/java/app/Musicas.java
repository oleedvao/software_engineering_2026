package app;

import core.dto.CreateArtistRequest;
import core.dto.GetArtistSongsWithLengthRequest;
import core.dto.GetArtistSongsWithLengthResult;
import core.dto.SongDTO;
import core.exception.ArtistRepositoryException;
import core.exception.RequestException;
import core.port.ArtistRepositoryPort;
import core.service.ArtistService;
import storage.JSONFileArtistRepository;

import java.util.ArrayList;

public class Musicas {

    static void main(String[] args) {

        ArtistRepositoryPort artistRepository = new JSONFileArtistRepository();

        ArtistService artistService = new ArtistService(artistRepository);

        // API-endpoint /api/create_artist
        try {
            artistService.createArtist(new CreateArtistRequest("Ole"));
            GetArtistSongsWithLengthResult result = artistService.getArtistSongsWithLength(
                    new GetArtistSongsWithLengthRequest(
                        1, 200
                    ));

        }
        catch (ArtistRepositoryException e) {
            System.err.println("Something went wrong.");;
        }
        catch (RequestException e) {
            System.err.println("RequestException: " + e.getMessage());
        }

    }
}
