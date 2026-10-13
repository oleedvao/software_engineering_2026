package app;

import core.dto.CreateArtistRequest;
import core.dto.GetArtistSongsWithLengthRequest;
import core.dto.GetArtistSongsWithLengthResult;
import core.exception.ArtistRepositoryException;
import core.exception.RequestException;
import core.port.ArtistRepositoryPort;
import core.service.ArtistService;
import io.github.cdimascio.dotenv.Dotenv;
import storage.adapter.ArtistRepositoryMySQLAdapter;
import storage.database.MySQLDatabase;

import java.sql.Connection;

/*
NB!
Denne klassen er bare her for å konseptuelt vise hvordan klassene henger sammen.
Vil ikke fungere som en kontinuerlig applikasjon. Benytt et rammeverk for en webapplikasjon, f.eks. Javalin.
 */
public class Musicas {

    static void main(String[] args) {
        /*
        Environment variables
         */
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        /*
        Database connection
         */
        MySQLDatabase mySQLDatabase = new MySQLDatabase(url, username, password);
        Connection connection = mySQLDatabase.startDB();

        /*
        Dependency injections
         */
        ArtistRepositoryPort artistRepository = new ArtistRepositoryMySQLAdapter(connection);
        ArtistService artistService = new ArtistService(artistRepository);


        // Tiltenkt API-endpoint /api/create_artist
        try {
            artistService.createArtist(new CreateArtistRequest("Ole"));

        }
        catch (ArtistRepositoryException e) {
            System.err.println("Something went wrong.");;
        }
        catch (RequestException e) {
            System.err.println("RequestException: " + e.getMessage());
        }


        // Tiltenkt API-endpoint /api/get-artist-songs-with-length
        try {
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
