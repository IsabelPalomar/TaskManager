package io.androidblog.simpletodo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.androidblog.simpletodo.R;
import io.androidblog.simpletodo.SimpleTodoApplication;
import io.androidblog.simpletodo.models.Item;

public class AddItemActivity extends AppCompatActivity {

    @Bind(R.id.editTextTitle)
    EditText editTextTitle;
    @Bind(R.id.editTextNoteDesc)
    EditText editTextNoteDesc;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SimpleTodoApplication app = (SimpleTodoApplication) getApplicationContext();

        databaseReference = app.getItemsReference();

    }

    @OnClick(R.id.fab)
    public void onClick() {

        String txtTitle = editTextTitle.getText().toString().trim();
        String txtDesc = editTextNoteDesc.getText().toString().trim();

        if (!txtTitle.isEmpty()) {
            Item toDoItem = new Item(txtTitle, txtDesc);
            databaseReference.push().setValue(toDoItem);
            finish();
        }
    }
}
