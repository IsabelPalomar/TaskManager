package io.androidblog.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    String position = "";
    String value = "";
    EditText etEditItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        position = getIntent().getStringExtra("position");
        value = getIntent().getStringExtra("value");
        etEditItem = (EditText) findViewById(R.id.etEditItem);
        etEditItem.setText(value);

    }

    public void onSubmit(View view) {
        Intent updateIntent = new Intent();
        updateIntent.putExtra("position", position);
        updateIntent.putExtra("value", etEditItem.getText().toString());
        updateIntent.putExtra("code", 200);
        setResult(RESULT_OK,  updateIntent);
        finish();
    }
}
