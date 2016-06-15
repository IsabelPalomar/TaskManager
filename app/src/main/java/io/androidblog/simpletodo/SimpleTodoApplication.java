package io.androidblog.simpletodo;

import com.firebase.client.Firebase;

public class SimpleTodoApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }

}