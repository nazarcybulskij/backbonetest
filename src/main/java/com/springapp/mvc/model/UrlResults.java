package com.springapp.mvc.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: NAZAR
 * Date: 16/05/14
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class UrlResults {
    public List<track> getResponse() {
        return response;
    }

    public void setResponse(List<track> response) {
        this.response = response;
    }

    List<track> response;
    static public  class track{
        public String getUrl() {
            return url;
        }

         public void setUrl(String url) {
            this.url = url;
        }

        String url;
    }

}
