/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.savedplayer;

import java.io.Serializable;

/**
 *
 * @author KHANHBQSE63463
 */
public class SavedPlayerDTO implements Serializable{
    private String username;
    private String fbref_url;

    public SavedPlayerDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFbref_url() {
        return fbref_url;
    }

    public void setFbref_url(String fbref_url) {
        this.fbref_url = fbref_url;
    }
}
