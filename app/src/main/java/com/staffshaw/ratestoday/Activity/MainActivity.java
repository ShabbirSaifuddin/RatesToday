package com.staffshaw.ratestoday.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.staffshaw.ratestoday.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*  BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/
    }

    public void Currencybtn(View view) {
        Intent i = new Intent(MainActivity.this, FragmentActivity.class);
        i.putExtra("type", "1");
        startActivity(i);
        finish();
    }

    public void Commoditiesbtn(View view) {
        Intent i = new Intent(MainActivity.this, FragmentActivity.class);
        i.putExtra("type", "2");
        startActivity(i);
        finish();
    }

    public void Forexbtn(View view) {
        Intent i = new Intent(MainActivity.this, FragmentActivity.class);
        i.putExtra("type", "3");
        startActivity(i);
        finish();
    }

    public void cryptobtn(View view) {
        Intent i = new Intent(MainActivity.this, FragmentActivity.class);
        i.putExtra("type", "4");
        startActivity(i);
        finish();
    }
}
