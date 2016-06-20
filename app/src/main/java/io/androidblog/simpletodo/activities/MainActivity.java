package io.androidblog.simpletodo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import org.apache.commons.io.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.androidblog.simpletodo.R;
import io.androidblog.simpletodo.SimpleTodoApplication;
import io.androidblog.simpletodo.adapters.ItemsRecyclerAdapter;
import io.androidblog.simpletodo.models.Item;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    private final int REQUEST_CODE = 20;

    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleTodoApplication app = (SimpleTodoApplication)getApplicationContext();

        databaseReference = app.getItemsReference();
        adapter = new ItemsRecyclerAdapter(R.layout.item, databaseReference);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            String newValue = data.getExtras().getString("value");
            String position = data.getExtras().getString("position");
            int intPosition =  Integer.parseInt(position);
            updateItem(intPosition, newValue);
        }
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                        items.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
        });

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                String value = (String) av.getItemAtPosition(pos);
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("position", String.valueOf(pos));
                i.putExtra("value", value);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

    }

    public void onAddItem(View view) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString().trim();

        if (!itemText.isEmpty()) {
            Item toDoItem = new Item(itemText);
            databaseReference.push().setValue(toDoItem);
        }
        etNewItem.setText("");

    }

    private void updateItem(int index, String value){
        items.set(index, value);
        itemsAdapter.notifyDataSetChanged();
        writeItems();
    }


    private void readItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");

        try{
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        }catch (IOException e){
            items = new ArrayList<String>();
        }
    }

    private void writeItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try{
            FileUtils.writeLines(todoFile, items);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
