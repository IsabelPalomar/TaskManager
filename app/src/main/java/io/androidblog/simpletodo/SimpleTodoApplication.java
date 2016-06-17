package io.androidblog.simpletodo;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.androidblog.simpletodo.utils.Constants;

public class SimpleTodoApplication extends android.app.Application {

    private DatabaseReference itemsReference;


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        itemsReference = database.getReference(Constants.FIREBASE_LOCATION_LIST_ITEMS);
    }

    public DatabaseReference getItemsReference() {
        return itemsReference;
    }
}