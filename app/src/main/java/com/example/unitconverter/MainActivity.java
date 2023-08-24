package com.example.unitconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CardView cardspeed,cardpressure,cardfrequency,cardarea,carddistance,cardcurrency,cardvolume,cardweight,cardtime,cardstorage,cardtemperature,cardnumbersystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        cardarea=findViewById(R.id.area);
        cardcurrency=findViewById(R.id.currency);
        carddistance=findViewById(R.id.distance);
        cardfrequency=findViewById(R.id.frequency);
        cardnumbersystem=findViewById(R.id.numbersystem);
        cardpressure=findViewById(R.id.pressure);
        cardspeed=findViewById(R.id.speed);
        cardstorage=findViewById(R.id.storage);
        cardtemperature=findViewById(R.id.temperature);
        cardtime=findViewById(R.id.time);
        cardvolume=findViewById(R.id.volume);
        cardweight=findViewById(R.id.weight);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_share) {
             Intent intent =new Intent();
             intent.setAction(Intent.ACTION_SEND);
             intent.putExtra(Intent.EXTRA_TEXT,"Hey check out my app Unit Converter");
             intent.setType("text/plain");
             startActivity(intent);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onclick(View v){
        Intent intent=new Intent(MainActivity.this,Convert.class);
        switch (v.getId()){
            case R.id.area:
                intent.putExtra("title","Area");
                break;
            case R.id.pressure:
                intent.putExtra("title","Pressure");

            case R.id.volume:
                intent.putExtra("title","Volume");
                break;
            case R.id.distance:
                intent.putExtra("title","Distance");
                break;
            case R.id.frequency:
                intent.putExtra("title","Frequency");
                break;
            case R.id.storage:
                intent.putExtra("title","Storage");
                break;
            case R.id.time:
                intent.putExtra("title","Time");
                break;
            case R.id.temperature:
                intent.putExtra("title","Temperature");
                break;
            case R.id.speed:
                intent.putExtra("title","Speed");
                break;
                case R.id.numbersystem:
                intent.putExtra("title","Number System");
                break;
            case R.id.weight:
                intent.putExtra("title","Weight");
                break;
            case R.id.currency:
                intent.putExtra("title","Currency");
                break;
        }
         startActivity(intent);
    }
}
