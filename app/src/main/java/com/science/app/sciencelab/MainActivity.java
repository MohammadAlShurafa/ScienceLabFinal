package com.science.app.sciencelab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle toggle;
    //NavigationView nav;
    //DrawerLayout drawer;
    Toolbar toolbar;
    FragmentManager fm;
    ShowDevicesFragment showDevicesFragment;
    Bundle bundle;
    Resources res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale("ar".toLowerCase()));
        res.updateConfiguration(conf, dm);

        initViews();
        initItems();
        initClicks();

    }

    private void initClicks() {
    /*    nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.mech) {
                    toolbar.setTitle(menuItem.getTitle());
                    showDevicesFragment.refresh(1);
                } else if (id == R.id.elec) {
                    toolbar.setTitle(menuItem.getTitle());
                    showDevicesFragment.refresh(2);
                } else if (id == R.id.static_elec) {
                    toolbar.setTitle(menuItem.getTitle());
                    showDevicesFragment.refresh(3);
                } else if (id == R.id.magn) {
                    toolbar.setTitle(menuItem.getTitle());
                    showDevicesFragment.refresh(4);
                } else if (id == R.id.temp) {
                    toolbar.setTitle(menuItem.getTitle());
                    showDevicesFragment.refresh(5);
                } else if (id == R.id.light) {
                    toolbar.setTitle(menuItem.getTitle());
                    showDevicesFragment.refresh(6);
                } else if (id == R.id.chem) {
                    toolbar.setTitle(menuItem.getTitle());
                    showDevicesFragment.refresh(7);
                } else if (id == R.id.biol) {
                    toolbar.setTitle(menuItem.getTitle());
                    showDevicesFragment.refresh(8);
                } else if (id == R.id.glass) {
                    toolbar.setTitle(menuItem.getTitle());
                    showDevicesFragment.refresh(9);
                } else if (id == R.id.helper) {
                    toolbar.setTitle(menuItem.getTitle());
                    showDevicesFragment.refresh(10);
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });*/
    }

    private void initItems() {

//        drawer.addDrawerListener(toggle);
        setSupportActionBar(toolbar);


        //toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, toolbar, R.string.app_name, R.string.app_name);
//        getSupportActionBar().setTitle("اجهزة ميكانيكية ");
        MyDbHelper helper = new MyDbHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Item> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select *from item", null);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));


        bundle = new Bundle();
        fm = getSupportFragmentManager();
        showDevicesFragment = new ShowDevicesFragment();
        bundle.putInt("num", getIntent().getIntExtra("id", 0));


        showDevicesFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().remove(showDevicesFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container, showDevicesFragment).commit();
//        nav.setCheckedItem(0);
        //      nav.setItemIconTintList(null);


    }

    private void initViews() {
//        nav = findViewById(R.id.nav_view);
//        ListView lv=findViewById(R.id.lv);
//        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar_main);
//        recyclerView=findViewById(R.id.since_rv);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showDevicesFragment.adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                showDevicesFragment.adapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().toString().equalsIgnoreCase("search")) {

        }
        return super.onOptionsItemSelected(item);
    }
}


