package io.androidblog.simpletodo.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.Field;

import io.androidblog.simpletodo.R;
import io.androidblog.simpletodo.activities.ItemListActivity;
import io.androidblog.simpletodo.models.Category;
import io.androidblog.simpletodo.utils.Constants;

public class CategoriesRecyclerAdapter extends FirebaseRecyclerAdapter<Category,
        CategoriesRecyclerAdapter.ItemViewHolder> {

    Context context;

    public CategoriesRecyclerAdapter(int modelLayout, DatabaseReference ref) {
        super(Category.class, modelLayout, CategoriesRecyclerAdapter.ItemViewHolder.class, ref);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(mModelLayout, parent, false);
        context = parent.getContext();
        return new ItemViewHolder(view);
    }

    @Override
    protected void populateViewHolder(ItemViewHolder holder, Category item, int position) {
        String itemDescription = item.getName();
        holder.txtItem.setText(itemDescription);
        holder.cvCategory.setBackgroundResource(getResId(item.getImage(), R.drawable.class));
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field field = c.getField(resName);
            return  field.getInt(null);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener,
            View.OnLongClickListener {

        TextView txtItem;
        ImageView imgDone;
        CardView cvCategory;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cvCategory = (CardView) itemView.findViewById(R.id.cvCategory);
            txtItem = (TextView) itemView.findViewById(R.id.txtItem);
            imgDone = (ImageView) itemView.findViewById(R.id.imgDone);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {

            String categoryId = getRef(getAdapterPosition()).getKey();

            Intent intent = new Intent(context, ItemListActivity.class);
            intent.putExtra(Constants.KEY_CATEGORY_ID, categoryId);
            context.startActivity(intent);

        }

        @Override
        public boolean onLongClick(View view) {
            return true;
        }
    }
}
