package com.staffshaw.ratestoday.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.staffshaw.ratestoday.APIs.CryptoAPI;
import com.staffshaw.ratestoday.R;

public class CryptoFragment extends Fragment implements View.OnClickListener {

    static RecyclerView recyclerView2;
    static ProgressBar progressBar2;
    androidx.appcompat.widget.SearchView search;
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.fragment_crypto, container, false);

        MobileAds.initialize(RootView.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d("Admob", "onInitializationComplete:");
            }
        });
        mAdView = RootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        recyclerView2 = RootView.findViewById(R.id.crypto_recyclerview);
        search = RootView.findViewById(R.id.search);
        progressBar2 = RootView.findViewById(R.id.progressbar);
        search.setOnClickListener(this);
        GetCrypto(RootView.getContext(), recyclerView2, progressBar2, "USD");

        final SwipeRefreshLayout pullToRefresh = RootView.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(CryptoFragment.this.getContext(), "Refreshing...", Toast.LENGTH_SHORT).show();
                search.onActionViewCollapsed();
                CryptoFragment.this.GetCrypto(RootView.getContext(), recyclerView2, progressBar2, "USD");
                pullToRefresh.setRefreshing(false);
            }
        });

        return RootView;
    }

    public void GetCrypto(Context context, RecyclerView recyclerView, ProgressBar progressBar, final String base) {

        CryptoAPI cryptoAPI = new CryptoAPI();
        cryptoAPI.getCrypto(base, context, recyclerView, progressBar);
    }


    public void runfromadapter(final String abc, Context context) {
        GetCrypto(context, recyclerView2, progressBar2, abc);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                search.onActionViewExpanded();
                CryptoAPI cryptoAPI = new CryptoAPI();
                cryptoAPI.Search(search, getContext());
                break;
        }
    }

    public interface OnFragmentInteractionListener {
    }
}
