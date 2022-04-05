package com.shah.fnalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.shah.fnalproject.UI.FvrtFragment;
import com.shah.fnalproject.UI.acitivty_persnol;
import com.shah.fnalproject.UI.activity1;
import com.shah.fnalproject.UI.activity2;
import com.shah.fnalproject.UI.activity3;
import com.shah.fnalproject.UI.activity4;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager FM;
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    TextView user_name;
    ImageView btnEditUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home");

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        FM = getSupportFragmentManager();

        navigationView.setCheckedItem(R.id.home);

        FM.beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_container_view, activity1.class, null).addToBackStack(null)
                .commit();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                FM.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, activity1.class, null)
                        .commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Home");

                break;
            case R.id.nav_listview:
                FM.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, activity3.class, null)
                        .commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.ic_list);
                break;
            case R.id.nav_add:
                FM.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, activity2.class, null)
                        .commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.add);
                break;
            case R.id.nav_list_online:
                FM.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, activity4.class, null)
                        .commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.live_data);
                break;
            case R.id.nav_fvrt:
                FM.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, FvrtFragment.class, null)
                        .commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.favroties);
                break;

            case R.id.nav_info:
                FM.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, acitivty_persnol.class, null)
                        .commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("About - author - Version 1.1");
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FM.beginTransaction().setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, activity1.class, null)
                .commit();
        Objects.requireNonNull(MainActivity.this.getSupportActionBar()).setTitle("Home");
    }


}