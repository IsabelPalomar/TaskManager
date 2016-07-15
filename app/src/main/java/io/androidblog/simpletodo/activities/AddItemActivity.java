package io.androidblog.simpletodo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import io.androidblog.simpletodo.models.Priority;

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
    private DatabaseReference categoriesReference;
    ArrayList<Category> categoryList = new ArrayList<>();
    ArrayList<Priority> priorityList = new ArrayList<>();
    int categorySelectedId;
    int prioritySelectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SimpleTodoApplication app = (SimpleTodoApplication) getApplicationContext();
        databaseReference = app.getItemsReference();
        categoriesReference = app.getCategoriesReference();

        setCategorySpinnerData();
        setPrioritySpinnerData();

    }

    private void setCategorySpinnerData() {

        categoryList.add(new Category(1, "Home", ""));
        categoryList.add(new Category(2, "Travel", ""));
        categoryList.add(new Category(3, "Groceries", ""));
        categoryList.add(new Category(4, "Work", ""));
        categoryList.add(new Category(5, "General", ""));

        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);
        spinnerCategory.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelectedId = categoryList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

    }

    private void setPrioritySpinnerData() {

        priorityList.add(new Priority(1, "Low", 1));
        priorityList.add(new Priority(2, "Medium", 2));
        priorityList.add(new Priority(3, "High", 3));

        ArrayAdapter<Priority> adapter = new ArrayAdapter<Priority>(this, android.R.layout.simple_spinner_dropdown_item, priorityList);
        spinnerPriority.setAdapter(adapter);

        spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prioritySelectedId = priorityList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

    }

    @OnClick(R.id.fab)
    public void onClick() {
        String txtTitle = editTextTitle.getText().toString().trim();
        String txtDesc = editTextNoteDesc.getText().toString().trim();
        if (!txtTitle.isEmpty()) {
            Item toDoItem = new Item(txtTitle, txtDesc, categorySelectedId, prioritySelectedId);
            databaseReference.push().setValue(toDoItem);
            finish();
        }
    }
}
