package com.example.hiringworkshop.DatabaseRepo;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmDB {
    /**
     * Application context
     */
    private Context context;

    /**
     * Constructor
     *
     * @param context application context
     */
    public RealmDB(Context context) {
        this.context = context;
    }

    /**
     * To create a realm db if the db does not exist..
     */
    public void setUpRealm() {
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("ThoughtWorks.Workshop")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
