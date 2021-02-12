
package com.josue_martinez.tiradadados.app;


import android.app.Application;

import com.josue_martinez.tiradadados.model.LayoutTirada;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
//Clase necesaria para generar la base de datos
public class MyApplication extends Application {
    public static AtomicInteger tiradaId = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();
        setUpRealmConfig();
        Realm realm = Realm.getDefaultInstance();
        tiradaId = getIdByTable(realm, LayoutTirada.class);
        realm.close();


    }

    private void setUpRealmConfig(){
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();

        if (results.size()>0){
            return new AtomicInteger(results.max("id").intValue());
        }
        else{
            return new AtomicInteger(0);
        }

    }
}