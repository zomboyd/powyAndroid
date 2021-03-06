package com.example.alex.powy;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.powy.controller.TransformBMP;
import com.example.alex.powy.fragment.aroundMeFragment;
import com.example.alex.powy.fragment.bagInfoFragment;
import com.example.alex.powy.fragment.dashboardFragment;
import com.example.alex.powy.fragment.ownerFragment;
import com.example.alex.powy.fragment.settingsFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //private connectionController mConnectionController;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null)
                return;
        }

        Fragment fragment;

        fragment = new dashboardFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        navigationView.addHeaderView(header);
        //battery
        setBattery(header);
        //home
        setDashboard(header);
        //bluetooth
        //mConnectionController = new connectionController(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment newFragment = null;

        int id = item.getItemId();

        if (id == R.id.dashboard) {
            newFragment = new dashboardFragment();
        } else if (id == R.id.owner) {
            newFragment = new ownerFragment();
        } else if (id == R.id.my_bag) {
            newFragment = new bagInfoFragment();
        } else if (id == R.id.around_me) {
            newFragment = new aroundMeFragment();
        } else if (id == R.id.action_settings) {
            newFragment = new settingsFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setBattery(View header) {
        //battery settings
        Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent != null ? batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : 0;
        TextView batteryTxt = (TextView) header.findViewById(R.id.owner_txt);
        batteryTxt.setText(String.valueOf(level) + "%");
    }

    public void setDashboard(View header) {
        //set Dashboard fragment
        ImageView home_asset = (ImageView) header.findViewById(R.id.nav_pic);
        Picasso.with(header.getContext()).load(R.drawable.profil_pic).transform(new TransformBMP()).into(home_asset);
        home_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                Fragment newFragment = new dashboardFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    //public void ButtonOnClick(View v) {
    //switch (v.getId()) {
    //    case R.id.startB:
    //        mConnectionController.turnOn();
    //        break;
    //    case R.id.stopB:
    //        mConnectionController.turnOff();
    //        break;
    //case R.id.startC:
    //mConnectionController.visibleOn();
    //  mConnectionController.discoverable();
    // Log.d("DISCO", String.valueOf(mConnectionController.getDiscoverable()));
    //ListView li = (ListView) findViewById(R.id.listViewBluetooth);
    //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mConnectionController.getDiscoverable());
    //li.setAdapter(arrayAdapter);
    //break;
    //case R.id.stopD:
    //    mConnectionController.visibleOff();
    //    break;
    // }
}
