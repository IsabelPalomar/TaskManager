package io.androidblog.simpletodo.adapters;


import android.graphics.drawable.Drawable;
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
import io.androidblog.simpletodo.models.Category;

public class CategoriesRecyclerAdapter extends FirebaseRecyclerAdapter<Category,
        CategoriesRecyclerAdapter.ItemViewHolder> {

    public CategoriesRecyclerAdapter(int modelLayout, DatabaseReference ref) {
        super(Category.class, modelLayout, CategoriesRecyclerAdapter.ItemViewHolder.class, ref);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(mModelLayout, parent, false);
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
            int position = getAdapterPosition();
            Category currentItem = (Category)getItem(position);
            DatabaseReference reference = getRef(position);
            Log.d("Application id", reference.getKey());

        }

        @Override
        public boolean onLongClick(View view) {
            int position = getAdapterPosition();
            DatabaseReference reference = getRef(position);
            reference.removeValue();
            return true;
        }
    }
}
