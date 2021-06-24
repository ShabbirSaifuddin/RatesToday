package com.staffshaw.ratestoday.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.staffshaw.ratestoday.Fragment.CommoditiesFragment;
import com.staffshaw.ratestoday.Fragment.CryptoFragment;
import com.staffshaw.ratestoday.Fragment.CurrencyFragment;
import com.staffshaw.ratestoday.Fragment.ForexFragment;
import com.staffshaw.ratestoday.R;


public class FragmentActivity extends AppCompatActivity
        implements CurrencyFragment.OnFragmentInteractionListener,
        CommoditiesFragment.OnFragmentInteractionListener,
        ForexFragment.OnFragmentInteractionListener,
        CryptoFragment.OnFragmentInteractionListener {

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        switch (type) {
            case "1":
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new CurrencyFragment(), "createPost").addToBackStack(null).commit();
                break;
            case "2":
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new CommoditiesFragment(), "createPost").addToBackStack(null).commit();
                break;
            case "3":
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new ForexFragment(), "createPost").addToBackStack(null).commit();
                break;
            case "4":
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new CryptoFragment(), "createPost").addToBackStack(null).commit();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(FragmentActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
