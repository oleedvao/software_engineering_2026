package integrationtest;

import core.domain.Artist;
import core.domain.Song;
import core.dto.GetArtistSongsWithLengthResult;
import core.dto.SongDTO;
import org.junit.jupiter.api.*;
import storage.adapter.ArtistRepositoryMySQLAdapter;
import testdb.H2TestDatabase;
import testdb.TestContainerDatabase;
import testdb.TestDatabase;

import java.sql.Connection;
import java.util.ArrayList;

public class ArtistRepositoryMySQLAdapterIntegrationTests {


    private final static TestDatabase testDB = new H2TestDatabase();
    // private final static TestDatabase testDB = new TestContainerDatabase();

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
         //Arrange
         Artist artist = new Artist("Ween");

         // Act
         artistRepository.createArtist(artist);

         // Assert
         Assertions.assertEquals(2, testDB.countRowsInTable("artists"));
         Assertions.assertEquals("Ween", testDB.getArtistName(2));
     }

    @Test
    public void getArtistSongs_ArtistSongsRetrievedSuccessfully() throws Exception{
        // Arrange
        int artistId = 1;

        // Act
        ArrayList<Song> artistSongs = artistRepository.getArtistSongs(artistId);

        // Assert
        Assertions.assertEquals(3, artistSongs.size());

        ArrayList<String> songTitles = getSongTitlesFromSongList(artistSongs);
        Assertions.assertTrue(songTitles.contains("Reckoner"));
        Assertions.assertTrue(songTitles.contains("Weird Fishes"));
        Assertions.assertTrue(songTitles.contains("Faust Arp"));
    }

    private ArrayList<String> getSongTitlesFromSongList(ArrayList<Song> songs) {
        ArrayList<String> songTitles = new ArrayList<>();

        for (Song song : songs) {
            songTitles.add(song.getTitle());
        }

        return songTitles;
    }

}
