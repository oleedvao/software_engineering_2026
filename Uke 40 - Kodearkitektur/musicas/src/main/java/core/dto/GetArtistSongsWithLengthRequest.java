package core.dto;

import core.exception.RequestException;

public class GetArtistSongsWithLengthRequest {

    private final int artistId;
    private final int lengthInSeconds;

    public GetArtistSongsWithLengthRequest(int artistId, int lengthInSeconds) throws RequestException{
        if (artistId < 1) {
            throw new RequestException("Artist id must be greater than zero.");
        }
        else if (lengthInSeconds < 1) {
            throw new RequestException("Song length in seconds must be greater than zero.");
        }

        this.artistId = artistId;
        this.lengthInSeconds = lengthInSeconds;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getLengthInSeconds() {
        return lengthInSeconds;
    }
}
