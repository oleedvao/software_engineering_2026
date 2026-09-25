package core.service;

import core.domain.Artist;
import core.domain.Song;
import core.dto.SongDTO;
import core.port.ArtistRepositoryPort;
import core.exception.ArtistRepositoryException;

import java.util.ArrayList;

public class ArtistService {

    ArtistRepositoryPort artistRepository;

    public ArtistService(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void createArtist(String name) throws ArtistRepositoryException {
        Artist artist = new Artist(name);

        // Save object persistently
        artistRepository.createArtist(artist);
    }

    public ArrayList<SongDTO> getArtistSongsWithLength(int artistId, int lengthInSeconds) throws ArtistRepositoryException{

        ArrayList<Song> artistSongs = artistRepository.getArtistSongs(artistId);

        ArrayList<SongDTO> artistSongsWithLength = new ArrayList<>();

        // receive and filter results
        for (Song song : artistSongs) {
            if (song.getLengthInSeconds() >= lengthInSeconds) {
                SongDTO songDTO = new SongDTO(
                        song.getId(),
                        song.getTitle(),
                        song.getLengthInSeconds()
                );
                artistSongsWithLength.add(songDTO);
            }
        }

        // return filtered results
        return artistSongsWithLength;
    }

}
