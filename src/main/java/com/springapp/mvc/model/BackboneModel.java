package com.springapp.mvc.model;

/**
 * Created with IntelliJ IDEA.
 * User: NAZAR
 * Date: 12/05/14
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class BackboneModel {

    public BackboneModel(String url, String artist, String track) {
        this.url = url;
        this.artist = artist;
        this.track = track;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    String url;
    String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    String track;
}
