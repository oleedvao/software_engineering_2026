package core.dto;

import core.exception.RequestException;

public class CreateArtistRequest {

    private final String name;

    public CreateArtistRequest(String name) throws RequestException{
        if (name == null) {
            throw new RequestException("Name must be specified.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
