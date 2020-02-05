package com.science.app.sciencelab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
    MyRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    Intent intent;
    Item item;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        initViews();

        List<Item> deviceName = new ArrayList<>();
        Item item1 = new Item(1,"أجهزة ميكانيكية","",1,R.drawable.ic_mec);
        Item item2 = new Item(2,"أجهزة كهربائية","",2,R.drawable.ic_electr);
        Item item3 = new Item(3,"كهرباء ساكنة","",3,R.drawable.ic_static);
        Item item4 = new Item(4,"أجهزة مغناطيسية","",4,R.drawable.ic_magnetic);
        Item item5 = new Item(5,"أجهزة حرارة","",5,R.drawable.temp);
        Item item6 = new Item(6,"أجهزة ضوئية","",6,R.drawable.ic_light);
        Item item7 = new Item(7,"أجهزة كيميائية","",7,R.drawable.ic_chem);
        Item item8 = new Item(8,"أجهزة أحياء","",8,R.drawable.ic_biology);
        Item item9 = new Item(9,"زجاجيات","",9,R.drawable.ic_glass);
        Item item10 = new Item(10,"أجهزة مساعدة","",10,R.drawable.ic_helper);

        deviceName.add(item1);
        deviceName.add(item2);
        deviceName.add(item3);
        deviceName.add(item4);
        deviceName.add(item5);
        deviceName.add(item6);
        deviceName.add(item7);
        deviceName.add(item8);
        deviceName.add(item9);
        deviceName.add(item10);


        RecyclerView recyclerView = findViewById(R.id.since_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, deviceName);


        recyclerView.setAdapter(adapter);
    }

    private void initViews() {

        recyclerView=findViewById(R.id.since_rv);
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

    }

    public void onBackPressed() {
            super.onBackPressed();
            finish();
            finishAffinity();
    }

}

