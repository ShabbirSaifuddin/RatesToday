package com.staffshaw.ratestoday.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.staffshaw.ratestoday.Model.CoalModel;
import com.staffshaw.ratestoday.Model.CottonModel;
import com.staffshaw.ratestoday.Model.GoldModel;
import com.staffshaw.ratestoday.Model.OilModel;
import com.staffshaw.ratestoday.Model.SilverModel;
import com.staffshaw.ratestoday.R;

public class DescriptionActivity extends AppCompatActivity {

    TextView name1;
    TextView source1;
    TextView price1;
    TextView date1;
    TextView decription1;
    ImageView image1;
    String type;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        name1 = findViewById(R.id.name4);
        source1 = findViewById(R.id.source4);
        price1 = findViewById(R.id.price4);
        date1 = findViewById(R.id.date4);
        decription1 = findViewById(R.id.description4);
        image1 = findViewById(R.id.image4);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        switch (type) {
            case "1":
                image1.setImageResource(R.drawable.gold5);
                name1.setText(GoldModel.Name.get(0));
                source1.setText(GoldModel.Source.get(0));
                price1.setText("$ " + GoldModel.Value.get(0));
                date1.setText(GoldModel.Time.get(0));
                decription1.setText(GoldModel.Description.get(0));
                break;
            case "2":
                image1.setImageResource(R.drawable.silver2);
                name1.setText(SilverModel.Name.get(0));
                source1.setText(SilverModel.Source.get(0));
                price1.setText("$ " + SilverModel.Value.get(0));
                date1.setText(SilverModel.Time.get(0));
                decription1.setText(SilverModel.Description.get(0));
                break;
            case "3":
                image1.setImageResource(R.drawable.oil2);
                name1.setText(OilModel.Name.get(0));
                source1.setText(OilModel.Source.get(0));
                price1.setText("$ " + OilModel.Value.get(0));
                date1.setText(OilModel.Time.get(0));
                decription1.setText(OilModel.Description.get(0));
                break;
            case "4":
                image1.setImageResource(R.drawable.cotton2);
                name1.setText(CottonModel.Name.get(0));
                source1.setText(CottonModel.Source.get(0));
                price1.setText("$ " + CottonModel.Value.get(0));
                date1.setText(CottonModel.Time.get(0));
                decription1.setText(CottonModel.Description.get(0));
                break;
            case "5":
                image1.setImageResource(R.drawable.coal2);
                name1.setText(CoalModel.Name.get(0));
                source1.setText(CoalModel.Source.get(0));
                price1.setText("$ " + CoalModel.Value.get(0));
                date1.setText(CoalModel.Time.get(0));
                decription1.setText(CoalModel.Description.get(0));
                break;
        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d("Admob", "onInitializationComplete:");
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }
}
