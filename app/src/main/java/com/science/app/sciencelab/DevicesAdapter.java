package com.science.app.sciencelab;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private ArrayList<Item> deviceItems;
    private ArrayList<Item> filterDeviceItems;
    Intent intent;

    DevicesAdapter(Context context, ArrayList<Item> deviceItems) {
        this.context = context;
        this.deviceItems = deviceItems;
        this.filterDeviceItems = deviceItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.device_item, parent, false);

        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Item id = filterDeviceItems.get(position);
        holder.deviceNameTv.setText(id.getName());
        holder.devicePhotoIm.setImageResource(id.getImageId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id", id.getId());
                intent.putExtra("department_num", id.getDepartmentNum());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterDeviceItems.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    filterDeviceItems = deviceItems;
                } else {
                    ArrayList<Item> filteredList = new ArrayList<>();
                    for (Item row : deviceItems) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getDetails().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    filterDeviceItems =  filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterDeviceItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterDeviceItems = (ArrayList<Item>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView deviceNameTv;
        ImageView devicePhotoIm;
        CardView cardView;
        Toolbar toolbar;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceNameTv = itemView.findViewById(R.id.deviceNameTv);
            devicePhotoIm = itemView.findViewById(R.id.devicePhotoIm);
            cardView = itemView.findViewById(R.id.cv);
            toolbar = itemView.findViewById(R.id.toolbar_main);
        }
    }

    public void refresh(ArrayList<Item> items) {
        this.deviceItems.clear();
        this.deviceItems.addAll(items);
        notifyDataSetChanged();
    }
}

