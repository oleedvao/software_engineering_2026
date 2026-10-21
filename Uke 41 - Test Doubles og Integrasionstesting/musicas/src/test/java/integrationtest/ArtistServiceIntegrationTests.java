package integrationtest;

import core.dto.CreateArtistRequest;
import core.dto.GetArtistSongsWithLengthRequest;
import core.dto.GetArtistSongsWithLengthResult;
import core.dto.SongDTO;
import core.service.ArtistService;
import org.junit.jupiter.api.*;
import storage.adapter.ArtistRepositoryMySQLAdapter;
import testdb.H2TestDatabase;
import testdb.TestContainerDatabase;
import testdb.TestDatabase;

import java.sql.Connection;
import java.util.ArrayList;

public class ArtistServiceIntegrationTests {

    private final static TestDatabase testDB = new H2TestDatabase();
    //private final static TestDatabase testDB = new TestContainerDatabase();

    private static Connection connection;

    private static ArtistRepositoryMySQLAdapter artistRepository;

    @BeforeAll
    public static void setUpTestDB() throws Exception{
        connection = testDB.startDB();
        testDB.createTables();

        //connection.setAutoCommit(false);

        artistRepository = new ArtistRepositoryMySQLAdapter(connection);
    }

    @BeforeEach
    public void prepareTest() throws Exception{
        testDB.createDummyData();
    }

    @AfterEach
    public void cleanUpTest() throws Exception {
        //connection.rollback();
        testDB.truncateTables();
    }

    @AfterAll
    public static void tearDownTestDB() throws Exception{
        testDB.stopDB();
    }

    @Test
    public void createArtist_ArtistIsCreatedSuccessfully() throws Exception {

        // Arrange
        ArtistService artistService = new ArtistService(artistRepository);
        CreateArtistRequest request = new CreateArtistRequest("Ween");

        // Act
        artistService.createArtist(request);

        // Assert
        Assertions.assertEquals(2, testDB.countRowsInTable("artists"));
        Assertions.assertEquals("Ween", testDB.getArtistName(2));
        Assertions.assertEquals(0, 1);
    }

    @Test
    public void getArtistSongsWithLength_ArtistSongsWithMinimumLengthRetrievedSuccessfully() throws Exception{

        // Arrange
        ArtistService artistService = new ArtistService(artistRepository);
        GetArtistSongsWithLengthRequest request = new GetArtistSongsWithLengthRequest(
                1, 200
        );

        // Act
        GetArtistSongsWithLengthResult result = artistService.getArtistSongsWithLength(request);

        // Assert
        Assertions.assertEquals(2, result.getSongDTOs().size());

        ArrayList<String> songTitles = getSongTitlesFromResult(result);
        Assertions.assertTrue(songTitles.contains("Reckoner"));
        Assertions.assertTrue(songTitles.contains("Weird Fishes"));

        Assertions.assertFalse(songTitles.contains("Faust Arp"));
    }

    private ArrayList<String> getSongTitlesFromResult(GetArtistSongsWithLengthResult result) {
        ArrayList<String> songTitles = new ArrayList<>();

        for (SongDTO song : result.getSongDTOs()) {
            songTitles.add(song.getTitle());
        }

        return songTitles;
    }

}
