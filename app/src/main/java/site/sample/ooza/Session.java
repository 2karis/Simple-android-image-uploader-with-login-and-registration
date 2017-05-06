package site.sample.ooza;

import android.content.Context;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by simple on 05/02/2017.
 */

public class Session {
    private String userId ;
    private boolean loggedIn;
    public void startSession(String id) {
        this.userId = id;
        this.loggedIn = true;
    }
    public void endSession(){
        this.userId = null;
        this.loggedIn = false;
    }
    public boolean isLoggedIn(){
        return loggedIn;
    }
}
