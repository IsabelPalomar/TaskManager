package io.androidblog.simpletodo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.androidblog.simpletodo.R;
import io.androidblog.simpletodo.SimpleTodoApplication;
import io.androidblog.simpletodo.adapters.CategoriesRecyclerAdapter;
import io.androidblog.simpletodo.adapters.ItemsRecyclerAdapter;
import io.androidblog.simpletodo.utils.Constants;

public class ItemListActivity extends AppCompatActivity {

    @Bind(R.id.rvItems)
    RecyclerView recyclerView;

    private String mCategoryId;
    private FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        mCategoryId = intent.getStringExtra(Constants.KEY_CATEGORY_ID);

        SimpleTodoApplication app = (SimpleTodoApplication) getApplicationContext();

        System.out.println(mCategoryId);

        Query itemsByCategoryId = app.getItemsReference().orderByChild(Constants.FIREBASE_LOCATION_ELEMENT_CATEGORY).equalTo(mCategoryId);
        adapter = new ItemsRecyclerAdapter(R.layout.item, itemsByCategoryId.getRef());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @OnClick(R.id.fab)
    public void onClick() {
        Intent i = new Intent(ItemListActivity.this, AddItemActivity.class);
        startActivity(i);
    }

}
