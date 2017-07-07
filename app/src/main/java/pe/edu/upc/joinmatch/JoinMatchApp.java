package pe.edu.upc.joinmatch;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

/**
 * Created by user on 7/6/17.
 */

public class JoinMatchApp extends Application {
    private static JoinMatchApp instance;

    public JoinMatchApp() {
        super();
        instance = this;
    }

    public static JoinMatchApp getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}

