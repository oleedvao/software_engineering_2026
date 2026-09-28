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

/*
ArtistService using DTOs for all parameters and return-values
 */
public class ArtistServiceWithDTOs {
    ArtistRepositoryPort artistRepository;

    public ArtistServiceWithDTOs(ArtistRepositoryPort artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void createArtist(CreateArtistRequest request) throws ArtistRepositoryException {
        Artist artist = new Artist(request.getName());

        // Save object persistently
        artistRepository.createArtist(artist);
    }

    public GetArtistSongsWithLengthResult getArtistSongsWithLength(GetArtistSongsWithLengthRequest request) throws ArtistRepositoryException{

        ArrayList<Song> artistSongs = artistRepository.getArtistSongs(request.getArtistId());

        ArrayList<SongDTO> artistSongsWithLength = new ArrayList<>();

        // receive and filter results
        for (Song song : artistSongs) {
            if (song.getLengthInSeconds() >= request.getLengthInSeconds()) {
                SongDTO songDTO = new SongDTO(
                        song.getId(),
                        song.getTitle(),
                        song.getLengthInSeconds()
                );
                artistSongsWithLength.add(songDTO);
            }
        }

        // return filtered results as DTO
        return new GetArtistSongsWithLengthResult(
                request.getArtistId(),
                request.getLengthInSeconds(),
                artistSongsWithLength
        );

    }
}
