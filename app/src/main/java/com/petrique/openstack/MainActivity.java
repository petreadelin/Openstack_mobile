package com.petrique.openstack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.petrique.openstack.OverviewFragment.OverviewFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //fara acest if de fiecare data cand rotim ecranul ne va muta in activitatea default
        if(savedInstanceState == null) {
            //cand deschidem programul va deschide activitatea aceasta prima
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new OverviewFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_overview);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.nav_overview:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OverviewFragment()).commit();
                break;

            case R.id.nav_instances:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InstancesFragment()).commit();
                break;

            case R.id.nav_flavors:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FlavorsFragment()).commit();
                break;

            case R.id.nav_images:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ImagesFragment()).commit();
                break;

            case R.id.nav_networks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NetworksFragment()).commit();
                break;

            case R.id.nav_subnets:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SubnetsFragment()).commit();
                break;

            case R.id.nav_routers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RoutersFragment()).commit();
                break;

            case R.id.nav_security:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SecurityFragment()).commit();
                break;

            /*
            case R.id.nav_networks:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
                */
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
