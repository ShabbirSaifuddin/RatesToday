package com.staffshaw.ratestoday.APIs;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.staffshaw.ratestoday.Adapters.CryptoAdapter;
import com.staffshaw.ratestoday.Connection.Connection;
import com.staffshaw.ratestoday.Model.CryptoModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CryptoAPI {

    static CryptoAdapter madapter;
    List<CryptoModel> list = new ArrayList<>();
    private Boolean Check;

    public void getCrypto(final String base, final Context context, final RecyclerView recyclerView, final ProgressBar progressBar) {

        list.clear();
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        if (Connection.isNetworkAvailable(context)) {

            if (base.equals("")) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Entries Cannot Be Empty", Toast.LENGTH_SHORT).show();
            } else {
                String URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?start=1&limit=100&convert=" + base;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonArray = jsonResponse.optJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject innerObject1 = jsonObject.getJSONObject("quote");
                                JSONObject innerObject2 = innerObject1.getJSONObject("USD");
                                String a = jsonObject.optString("name");
                                String b = jsonObject.optString("symbol");
                                String c = jsonObject.optString("cmc_rank");
                                String d = jsonObject.optString("last_updated");
                                String e = innerObject2.optString("price");

                                try {
                                    String price = e.substring(0, 8);
                                    Log.i("TAG", a + " " + b + " " + c + " " + d + " " + price);
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    list.add(new CryptoModel(jsonObject.optString("name"), jsonObject.optString("symbol"), jsonObject.optString("cmc_rank"), jsonObject.optString("last_updated"), price));
                                    CryptoAdapter adapter = new CryptoAdapter(list, context);
                                    recyclerView.setAdapter(adapter);
                                    madapter = adapter;
                                    recyclerView.setLayoutManager(new GridLayoutManager(context, 1));

                                } catch (Exception e1) {
                                    String price = e.substring(0, 1);
                                    Log.i("TAG", a + " " + b + " " + c + " " + d + " " + price);
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    list.add(new CryptoModel(jsonObject.optString("name"), jsonObject.optString("symbol"), jsonObject.optString("cmc_rank"), jsonObject.optString("last_updated"), price));
                                    CryptoAdapter adapter = new CryptoAdapter(list, context);
                                    recyclerView.setAdapter(adapter);
                                    madapter = adapter;
                                    recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
                                }

//                                Log.i("TAG", a + " " + b + " " + c + " " + d + " " + price);
//                                progressBar.setVisibility(View.GONE);
//                                recyclerView.setVisibility(View.VISIBLE);
//                                list.add(new CryptoModel(jsonObject.optString("name"), jsonObject.optString("symbol"), jsonObject.optString("cmc_rank"), jsonObject.optString("last_updated"), price));
//                                CryptoAdapter adapter = new CryptoAdapter(list, context);
//                                recyclerView.setAdapter(adapter);
//                                madapter = adapter;
//                                recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
                            }

                        } catch (Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(context, "Unexpected Error " + e, Toast.LENGTH_SHORT).show();
                            Check = false;
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        Check = false;
                    }
                }) {
                    public Map<String, String> getHeaders() {
//                ///Posting params to register url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("X-CMC_PRO_API_KEY", "24b87912-6aa8-482f-8d10-ee03ded279cb");
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);

            }
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context, "No internet Connection", Toast.LENGTH_SHORT).show();
            Check = false;
        }
    }

    public void Search(SearchView search, final Context context) {
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (madapter != null) {
                    madapter.getFilter().filter(query);
                } else {
                    Toast.makeText(context, "adapter == null", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (madapter != null) {
                    madapter.getFilter().filter(newText);
                } else {
                    Toast.makeText(context, "adapter == null", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


    }


}
