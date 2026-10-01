package app;

import core.dto.CreateArtistRequest;
import core.exception.ArtistRepositoryException;
import core.port.ArtistRepositoryPort;
import core.service.ArtistService;
import core.service.ArtistServiceWithDTOs;
import storage.JSONFileArtistRepository;

public class Musicas {

    static void main(String[] args) {

        ArtistRepositoryPort artistRepository = new JSONFileArtistRepository();

        //ArtistService artistService = new ArtistService(artistRepository);
        ArtistServiceWithDTOs artistService = new ArtistServiceWithDTOs(artistRepository);

        // API-endpoint /api/create_artist
        try {
            //artistService.createArtist("Ole");
            artistService.createArtist(new CreateArtistRequest("Ole"));
        }
        catch (ArtistRepositoryException e) {
            System.err.println("Something went wrong.");;
        }

    }
}
