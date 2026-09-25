package core.domain;

import core.Song;

import java.util.ArrayList;

public class Album {
    private int id;
    private String title;
    private ArrayList<Song> songs;

    public Album(String title, ArrayList<core.domain.Song> songs) {
        this.title = title;
        this.songs = songs;
    }

    public Album(int id, String title, ArrayList<core.domain.Song> songs) {
        this.id = id;
        this.title = title;
        this.songs = songs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<core.domain.Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public void setSongs(ArrayList<core.domain.Song> songs) {
        this.songs = songs;
    }
}
