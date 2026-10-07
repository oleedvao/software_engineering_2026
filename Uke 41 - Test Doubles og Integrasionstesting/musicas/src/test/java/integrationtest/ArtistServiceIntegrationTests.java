package integrationtest;

import core.dto.CreateArtistRequest;
import core.dto.GetArtistSongsWithLengthRequest;
import core.dto.GetArtistSongsWithLengthResult;
import core.service.ArtistService;
import org.junit.jupiter.api.*;
import storage.adapter.ArtistRepositoryMySQLAdapter;
import testdb.TestContainerDatabase;
import testdb.TestDatabase;

import java.sql.Connection;

public class ArtistServiceIntegrationTests {

    private final static TestDatabase testDB = new TestContainerDatabase();

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
        Assertions.assertEquals("Reckoner", result.getSongDTOs().get(0).getTitle());
        Assertions.assertEquals("Weird Fishes", result.getSongDTOs().get(1).getTitle());
    }

}
