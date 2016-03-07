package com.example.ankitgarg.gifsearch;

import android.app.Application;
import android.content.Context;

import com.example.ankitgarg.gifsearch.network.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ankitgarg on 3/5/16.
 */
public class GifSearchApplication extends Application {

    private static Realm mRealm;
//    private static RequestQueue mQueue;
    private static GifSearchApplication mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.setAppContext(getApplicationContext());
        // Create a RealmConfiguration which is to locate Realm file in package's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        // Get a Realm instance for this thread
        mRealm = Realm.getInstance(realmConfig);
        // Check if db has been created and add data to db if it doesnt exist.
        RealmHelper.addDatatoDB(this);
//        mQueue = Volley.newRequestQueue(this);

    }

    public static Realm getmRealm() {
        return mRealm;
    }

//    public static RequestQueue getmQueue() {
//        return mQueue;
//    }

    public static GifSearchApplication getInstance(){
        return mInstance;
    }
    public static Context getAppContext() {
        return mAppContext;
    }
    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }
}
