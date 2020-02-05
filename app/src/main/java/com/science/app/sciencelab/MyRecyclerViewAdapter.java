package com.science.app.sciencelab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Item> since;
    private LayoutInflater inflater;
    private ItemClickListener listener;
    private Context context;
    Intent intent;


    MyRecyclerViewAdapter(Context context, List<Item> since) {
        this.inflater = LayoutInflater.from(context);
        this.since = (ArrayList<Item>) since;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        final String device = since.get(position);
//        holder.myTextView.setText(device);
//        holder.text.setText(device);
//        holder.image.setImageResource(device.);

        final Item device = since.get(position);
        holder.text.setText(device.getName());
        holder.image.setImageResource(device.getImageId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, MainActivity.class);
                intent.putExtra("id", position + 1);
                intent.putExtra("title", device.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return since.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        TextView myTextView;
//        Button button;
        TextView text;
        ImageView image;


        ViewHolder(View itemView) {
            super(itemView);
//            myTextView = itemView.findViewById(R.id.item);
            text = itemView.findViewById(R.id.DeviceName_tv);
            image = itemView.findViewById(R.id.image2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) listener.onItemClick(view, getAdapterPosition());
        }
    }

    Item getItem(int id) {
        return since.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
