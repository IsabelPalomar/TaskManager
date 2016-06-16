package io.androidblog.simpletodo.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;


import java.util.HashMap;
import java.util.Map;

import io.androidblog.simpletodo.R;
import io.androidblog.simpletodo.models.Item;

public class ItemsRecyclerAdapter extends FirebaseRecyclerAdapter<Item,
        ItemsRecyclerAdapter.ItemViewHolder> {

    public ItemsRecyclerAdapter(int modelLayout, DatabaseReference ref) {
        super(Item.class, modelLayout, ItemsRecyclerAdapter.ItemViewHolder.class, ref);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(mModelLayout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    protected void populateViewHolder(ItemViewHolder holder, Item item, int position) {
        String itemDescription = item.getItem();

        holder.txtItem.setText(itemDescription);

        if (item.isCompleted()) {
            holder.imgDone.setVisibility(View.VISIBLE);
        } else {
            holder.imgDone.setVisibility(View.INVISIBLE);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener,
            View.OnLongClickListener {

        TextView txtItem;
        ImageView imgDone;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txtItem = (TextView) itemView.findViewById(R.id.txtItem);
            imgDone = (ImageView) itemView.findViewById(R.id.imgDone);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Item currentItem = (Item)getItem(position);
            DatabaseReference reference = getRef(position);
            boolean completed = !currentItem.isCompleted();

            currentItem.setCompleted(completed);
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("completed", completed);
            reference.updateChildren(updates);
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
