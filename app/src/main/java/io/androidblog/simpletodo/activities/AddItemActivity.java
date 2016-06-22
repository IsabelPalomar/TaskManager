package io.androidblog.simpletodo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.androidblog.simpletodo.R;
import io.androidblog.simpletodo.SimpleTodoApplication;
import io.androidblog.simpletodo.models.Category;
import io.androidblog.simpletodo.models.Item;
import io.androidblog.simpletodo.utils.Constants;

public class AddItemActivity extends AppCompatActivity {

    @Bind(R.id.editTextTitle)
    EditText editTextTitle;
    @Bind(R.id.editTextNoteDesc)
    EditText editTextNoteDesc;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.spinnerCategory)
    Spinner spinnerCategory;
    @Bind(R.id.spinnerPriority)
    Spinner spinnerPriority;

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

        setCategorySpinnerData();
        setPrioritySpinnerData();

    }

    private void setPrioritySpinnerData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Constants.PRIORITY_LIST);
        spinnerPriority.setAdapter(adapter);
    }

    private void setCategorySpinnerData() {

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Home", "home"));
        categoryList.add(new Category(2, "Travel", "travel"));
        categoryList.add(new Category(3, "Groceries", "groceries"));
        categoryList.add(new Category(4, "Work", "work"));
        categoryList.add(new Category(5, "General", "general"));

        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);
        spinnerCategory.setAdapter(adapter);

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
