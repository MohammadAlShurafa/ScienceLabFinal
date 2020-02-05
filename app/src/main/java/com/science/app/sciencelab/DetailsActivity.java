package com.science.app.sciencelab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuCompat;

@SuppressLint("Registered")
public class DetailsActivity extends AppCompatActivity {
    private ImageView iv_item;
    private TextView tv_name, tv_details;
    Item item;
    Intent intent;
    Toolbar toolbar;
    ShowDevicesFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailes);

        initViews();
        initItems();
        initClicks();

    }

    private void initClicks() {

    }

    private void initItems() {
        intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        fragment = new ShowDevicesFragment();
        if (id != 0) {
            item = new MyDbHelper(DetailsActivity.this).getItemById(id);
            iv_item.setImageResource(item.getImageId());
            tv_name.setText(item.getName());
            tv_details.setText(item.getDetails());
            getSupportActionBar().setTitle(item.getName());
            // toolbar.setNavigationIcon(R.drawable.ic_arrow_forward_black_24dp);
        }

    }

    private void initViews() {
        iv_item = findViewById(R.id.iv_item);
        tv_name = findViewById(R.id.tv_title);
        tv_details = findViewById(R.id.tv_details);
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_forward_black_24dp);

        //        toolbar.getTitleMarginBottom();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startNewActivity();
        }
        return super.onOptionsItemSelected(item);
    }


    private void startNewActivity(){
         Intent i = new Intent(DetailsActivity.this, MainActivity.class);
         startActivity(i);
         finish();
    }

}
