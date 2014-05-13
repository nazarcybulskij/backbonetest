package com.springapp.mvc.model;

/**
 * Created with IntelliJ IDEA.
 * User: NAZAR
 * Date: 08/05/14
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class token {
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    String access_token;

}
