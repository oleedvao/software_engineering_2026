package app;

import core.dto.SongDTO;
import core.exception.ArtistRepositoryException;
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
            artistService.createArtist("Ole");
            ArrayList<SongDTO> songDTOs = artistService.getArtistSongsWithLength(1, 200);

        }
        catch (ArtistRepositoryException e) {
            System.err.println("Something went wrong.");;
        }

    }
}
