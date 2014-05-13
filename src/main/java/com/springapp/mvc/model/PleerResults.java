package com.springapp.mvc.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: NAZAR
 * Date: 08/05/14
 * Time: 23:42
 * To change this template use File | Settings | File Templates.
 */
public class PleerResults {

    @Override
    public String toString() {
        return "{"+count+","+success+","+"}";
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    String success;
    String  count;


    public Map<String, track> getTracks() {
        return tracks;
    }

    public void setTracks(Map<String, track> tracks) {
        this.tracks = tracks;
    }

    Map<String,track> tracks;
    public class track{
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        String id;
        String artist;

        public String getTrack() {
            return track;
        }

        public void setTrack(String track) {
            this.track = track;
        }

        String track;

        @Override
        public String toString() {
            return "{"+id+","+artist+","+artist+"}";
        }
    }


}
