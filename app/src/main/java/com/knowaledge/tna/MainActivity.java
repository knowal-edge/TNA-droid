package com.knowaledge.tna;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowaledge.tna.NavigationFragments.ReportFragment;
import com.knowaledge.tna.TabFragments.TabFragment;

/**
 * Created by ananth on 10/23/2016.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    int k = 0;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navLayout;
    private String bak_msg;
    private Bitmap profilebitmap;
    private ImageView iv;
    private Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        toggle.syncState();
        navLayout = (NavigationView) findViewById(R.id.navLayout);
        navLayout.setNavigationItemSelectedListener(this);
        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.nav_header, null);
        TextView email =  header.findViewById(R.id.email);
        String name = getIntent().getStringExtra("Username");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",name);
        editor.apply();
        email.setText(name);
      /*  String name1 = getIntent().getStringExtra(KEY_EMAIL);
        text1.setText(name1);
        ImageView img = (ImageView) header.findViewById(R.id.picture);
        String ppic = getIntent().getStringExtra(KEY_IMAGE);
        Log.d("LOG_TAG", ppic);
        Picasso.with(this)
                .load(ppic)
                .into(img);
      */  //fetching image

        navLayout.addHeaderView(header);

        if (savedInstanceState == null) {
            navLayout.getMenu().performIdentifierAction(R.id.home, 0);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new TabFragment());
                ft.commit();
                break;
            case R.id.reports:
                FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                ft1.replace(R.id.frameLayout, new ReportFragment());
                ft1.commit();
                break;
            case R.id.logout:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Do you want to logout?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                break;
        }


        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();

        return false;
    }



    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Do you want to logout?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
