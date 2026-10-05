package core.service;

import core.domain.Artist;
import core.domain.Song;
import core.dto.CreateArtistRequest;
import core.dto.GetArtistSongsWithLengthRequest;
import core.dto.GetArtistSongsWithLengthResult;
import core.dto.SongDTO;
import core.port.ArtistRepositoryPort;
import core.exception.ArtistRepositoryException;

import java.util.ArrayList;
import java.util.Objects;

/*
ArtistService using simple parameters and return types instead of complicated DTOs.
See ArtistServiceWithDTOs for an even more loosely coupled example of the same class.
 */
public class ArtistService {

    ArtistRepositoryPort artistRepository;

    public ArtistService(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }


    public void createArtist(CreateArtistRequest request) throws ArtistRepositoryException {
        Objects.requireNonNull(request);

        Artist artist = new Artist(request.getName());

        // Save object persistently
        artistRepository.createArtist(artist);
    }


    public GetArtistSongsWithLengthResult getArtistSongsWithLength(GetArtistSongsWithLengthRequest request)
            throws ArtistRepositoryException{
        Objects.requireNonNull(request);

        ArrayList<Song> artistSongs = artistRepository.getArtistSongs(request.getArtistId());

        ArrayList<SongDTO> artistSongsWithLengthList = new ArrayList<>();

        // receive and filter results
        for (Song song : artistSongs) {
            if (song.getLengthInSeconds() >= request.getLengthInSeconds()) {
                SongDTO songDTO = new SongDTO(
                        song.getId(),
                        song.getTitle(),
                        song.getLengthInSeconds()
                );
                artistSongsWithLengthList.add(songDTO);
            }
        }

        // return filtered results
        return new GetArtistSongsWithLengthResult(
                request.getArtistId(),
                request.getLengthInSeconds(),
                artistSongsWithLengthList
        );
    }

}
