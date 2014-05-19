package com.springapp.mvc.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: NAZAR
 * Date: 28/04/14
 * Time: 00:14
 * To change this template use File | Settings | File Templates.
 */
public class VkResults {
    public tracklist getResponse() {
        return response;
    }

    public void setResponse(tracklist response) {
        this.response = response;
    }

    private tracklist response;



    static public class tracklist{
        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<track> getItems() {
            return items;
        }

        public void setItems(List<track> items) {
            this.items = items;
        }

        private int count;
        private List<track> items;

    }
    static public class track{



        public void setOwner_id(String owner_id) {
            this.owner_id = owner_id;
        }

        String  owner_id;
        public String getId() {
            return owner_id+"_"+id;
        }

        public void setId(String id) {
            this.id = id;
        }

        String   id;
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getUrl() {
            return "http://backnonetest-zhybulskij.rhcloud.com/mp3/"+getId();
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String title;
        private String artist;
        private String url;

    }



}
