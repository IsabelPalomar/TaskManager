package io.androidblog.simpletodo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.androidblog.simpletodo.utils.Constants;

public class SimpleTodoApplication extends android.app.Application {

    private DatabaseReference itemsReference;
    private DatabaseReference categoriesReference;


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        itemsReference = database.getReference(Constants.FIREBASE_LOCATION_LIST_ITEMS);
        categoriesReference = database.getReference(Constants.FIREBASE_LOCATION_LIST_CATEGORIES);
    }

    public DatabaseReference getItemsReference() {
        return itemsReference;
    }

    public DatabaseReference getCategoriesReference() {
        return categoriesReference;
    }
}