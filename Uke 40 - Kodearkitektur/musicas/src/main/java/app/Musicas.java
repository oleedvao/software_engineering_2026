package app;

import core.exception.ArtistRepositoryException;
import core.port.ArtistRepositoryPort;
import core.service.ArtistService;
import storage.JSONFileArtistRepository;
import storage.MySQLArtistRepository;

public class Musicas {

    static void main(String[] args) {

        ArtistRepositoryPort artistRepository = new JSONFileArtistRepository();

        ArtistService artistService = new ArtistService(artistRepository);

        // API-endpoint /api/create_artist
        try {
            artistService.createArtist("Ole");
        }
        catch (ArtistRepositoryException e) {
            System.err.println("Something went wrong.");;
        }

    }
}
