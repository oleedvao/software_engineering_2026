package storage;

import core.domain.Artist;
import core.domain.Song;
import core.exception.ArtistRepositoryException;
import core.port.ArtistRepositoryPort;

import java.util.ArrayList;

public class MySQLArtistRepository implements ArtistRepositoryPort {

    @Override
    public void createArtist(Artist artist) throws ArtistRepositoryException {

    }

    @Override
    public ArrayList<Song> getArtistSongs(int artistId) throws ArtistRepositoryException {
        return null;
    }
}
