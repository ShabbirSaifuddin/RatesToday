package com.staffshaw.ratestoday.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.staffshaw.ratestoday.APIs.CoalAPI;
import com.staffshaw.ratestoday.APIs.CottonAPI;
import com.staffshaw.ratestoday.APIs.GoldAPI;
import com.staffshaw.ratestoday.APIs.OilAPI;
import com.staffshaw.ratestoday.APIs.SilverAPI;
import com.staffshaw.ratestoday.Activity.DescriptionActivity;
import com.staffshaw.ratestoday.R;

public class CommoditiesFragment extends Fragment implements View.OnClickListener {

    LinearLayout layout1, layout2, layout3, layout4, layout5;
    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5;
    TextView name1, name2, name3, name4, name5;
    TextView source1, source2, source3, source4, source5;
    TextView price1, price2, price3, price4, price5;
    TextView date1, date2, date3, date4, date5;
    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View RootView = inflater.inflate(R.layout.fragment_commodities, container, false);

        layout1 = RootView.findViewById(R.id.layout1);
        progressBar1 = RootView.findViewById(R.id.progressbar1);
        name1 = RootView.findViewById(R.id.name1);
        source1 = RootView.findViewById(R.id.source1);
        price1 = RootView.findViewById(R.id.price1);
        date1 = RootView.findViewById(R.id.date1);

        layout2 = RootView.findViewById(R.id.layout2);
        progressBar2 = RootView.findViewById(R.id.progressbar2);
        name2 = RootView.findViewById(R.id.name2);
        source2 = RootView.findViewById(R.id.source2);
        price2 = RootView.findViewById(R.id.price2);
        date2 = RootView.findViewById(R.id.date2);

        layout3 = RootView.findViewById(R.id.layout3);
        progressBar3 = RootView.findViewById(R.id.progressbar3);
        name3 = RootView.findViewById(R.id.name3);
        source3 = RootView.findViewById(R.id.source3);
        price3 = RootView.findViewById(R.id.price3);
        date3 = RootView.findViewById(R.id.date3);

        layout4 = RootView.findViewById(R.id.layout4);
        progressBar4 = RootView.findViewById(R.id.progressbar4);
        name4 = RootView.findViewById(R.id.name4);
        source4 = RootView.findViewById(R.id.source4);
        price4 = RootView.findViewById(R.id.price4);
        date4 = RootView.findViewById(R.id.date4);

        layout5 = RootView.findViewById(R.id.layout5);
        progressBar5 = RootView.findViewById(R.id.progressbar5);
        name5 = RootView.findViewById(R.id.name5);
        source5 = RootView.findViewById(R.id.source5);
        price5 = RootView.findViewById(R.id.price5);
        date5 = RootView.findViewById(R.id.date5);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);

        MobileAds.initialize(RootView.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d("Admob", "onInitializationComplete:");
            }
        });
        mAdView = RootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final SwipeRefreshLayout pullToRefresh = RootView.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(RootView.getContext(), "Refreshing...", Toast.LENGTH_SHORT).show();
                RunAPI(RootView.getContext());
                pullToRefresh.setRefreshing(false);
            }
        });

        RunAPI(RootView.getContext());
        return RootView;
    }

    private void RunAPI(Context context) {

        GoldAPI goldAPI = new GoldAPI();
        goldAPI.getGold(context, layout1, name1, source1, price1, date1, progressBar1);

        SilverAPI silverAPI = new SilverAPI();
        silverAPI.getSilver(context, layout2, name2, source2, price2, date2, progressBar2);

        OilAPI oilAPI = new OilAPI();
        oilAPI.getOil(context, layout3, name3, source3, price3, date3, progressBar3);

        CottonAPI cottonAPI = new CottonAPI();
        cottonAPI.getCotton(context, layout4, name4, source4, price4, date4, progressBar4);

        CoalAPI coalAPI = new CoalAPI();
        coalAPI.getCoal(context, layout5, name5, source5, price5, date5, progressBar5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout1:
                Intent i = new Intent(getContext(), DescriptionActivity.class);
                i.putExtra("type", "1");
                startActivity(i);

                break;
            case R.id.layout2:
                Intent i2 = new Intent(getContext(), DescriptionActivity.class);
                i2.putExtra("type", "2");
                startActivity(i2);

                break;
            case R.id.layout3:
                Intent i3 = new Intent(getContext(), DescriptionActivity.class);
                i3.putExtra("type", "3");
                startActivity(i3);

                break;
            case R.id.layout4:
                Intent i4 = new Intent(getContext(), DescriptionActivity.class);
                i4.putExtra("type", "4");
                startActivity(i4);

                break;
            case R.id.layout5:
                Intent i5 = new Intent(getContext(), DescriptionActivity.class);
                i5.putExtra("type", "5");
                startActivity(i5);

                break;

        }
    }

    public interface OnFragmentInteractionListener {
    }
}
