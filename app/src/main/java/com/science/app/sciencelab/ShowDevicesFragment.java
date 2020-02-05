package com.science.app.sciencelab;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ShowDevicesFragment extends Fragment {
    private ArrayList<Item> deviceItems;
    DevicesAdapter adapter;
    LinearLayoutManager lm;
    GridLayoutManager grid;
    SearchView searchView;
    RecyclerView rc;

    public ShowDevicesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_show_devices, container, false);
        rc = v.findViewById(R.id.recyclerView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();

        assert bundle != null;
        deviceItems = new MyDbHelper(getContext()).getAllItemForDepartment(bundle.getInt("num"));

        adapter = new DevicesAdapter(getContext(), deviceItems);
        grid = new GridLayoutManager(getContext(), 2);
        grid.setOrientation(GridLayoutManager.VERTICAL);
        rc.setLayoutManager(grid);
        rc.setAdapter(adapter);

    }

    public void refresh(int i) {
        grid.scrollToPosition(0);
        deviceItems = new MyDbHelper(getContext()).getAllItemForDepartment(i);
        adapter.refresh(deviceItems);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().toString().equalsIgnoreCase("search")){

        }

        return super.onOptionsItemSelected(item);
    }
}
