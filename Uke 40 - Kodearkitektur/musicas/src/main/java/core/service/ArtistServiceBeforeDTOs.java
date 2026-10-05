package core.service;

import core.domain.Artist;
import core.domain.Song;
import core.dto.CreateArtistRequest;
import core.dto.GetArtistSongsWithLengthRequest;
import core.dto.GetArtistSongsWithLengthResult;
import core.dto.SongDTO;
import core.exception.ArtistRepositoryException;
import core.port.ArtistRepositoryPort;

import java.util.ArrayList;
import java.util.Objects;

public class ArtistServiceBeforeDTOs {

    ArtistRepositoryPort artistRepository;

    public ArtistServiceBeforeDTOs(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }


    public void createArtist(String name) throws ArtistRepositoryException {

        Artist artist = new Artist(name);

        // Save object persistently
        artistRepository.createArtist(artist);
    }


    public ArrayList<SongDTO> getArtistSongsWithLength(int artistId, int lengthInSeconds)
            throws ArtistRepositoryException{

        ArrayList<Song> artistSongs = artistRepository.getArtistSongs(artistId);

        ArrayList<SongDTO> artistSongsWithLengthList = new ArrayList<>();

        // receive and filter results
        for (Song song : artistSongs) {
            if (song.getLengthInSeconds() >= lengthInSeconds) {
                SongDTO songDTO = new SongDTO(
                        song.getId(),
                        song.getTitle(),
                        song.getLengthInSeconds()
                );
                artistSongsWithLengthList.add(songDTO);
            }
        }

        // return filtered results
        return artistSongsWithLengthList;
    }
}
