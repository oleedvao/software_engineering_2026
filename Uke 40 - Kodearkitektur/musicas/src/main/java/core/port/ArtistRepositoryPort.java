package core.port;

import core.domain.Artist;
import core.domain.Song;
import core.exception.ArtistRepositoryException;

import java.util.ArrayList;

public interface ArtistRepositoryPort {

    void createArtist(Artist artist) throws ArtistRepositoryException;
    ArrayList<Song> getArtistSongs(int artistId) throws ArtistRepositoryException;
}
