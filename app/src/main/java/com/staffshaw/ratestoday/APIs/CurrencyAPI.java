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
import com.staffshaw.ratestoday.Adapters.CurrencyAdapter;
import com.staffshaw.ratestoday.Connection.Connection;
import com.staffshaw.ratestoday.Fragment.CurrencyFragment;
import com.staffshaw.ratestoday.Model.CurrencyModel;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class CurrencyAPI {

    static CurrencyAdapter madapter;
    List<CurrencyModel> list = new ArrayList<>();
    private Boolean Check;

    public void getCurrency(final String base, final Context context, final RecyclerView recyclerView, final ProgressBar progressBar) {

        list.clear();
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        if (Connection.isNetworkAvailable(context)) {

            if (base.equals("")) {
                CurrencyFragment currencyFragment = new CurrencyFragment();
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Base Cannot Be Empty", Toast.LENGTH_SHORT).show();
            } else {
                String URL = "https://openexchangerates.org/api/latest.json?app_id=96cb7e699fb840ebbfb1d682e1304b31&base=" + base;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONObject jsonResponse2 = new JSONObject(response).getJSONObject("rates");

                            long dv = Long.valueOf(jsonResponse.optString("timestamp")) * 1000;// its need to be in milisecond
                            Date df = new java.util.Date(dv);
                            String vv = new SimpleDateFormat("dd-MM-yyyy hh:mma").format(df);

                            Log.d("Currency API", "onResponse: " + vv);

                            Iterator<String> iterator = jsonResponse2.keys();
                            while (iterator.hasNext()) {
                                String key = iterator.next();
                                Log.i("TAG", "key:" + key + "--Value::" + jsonResponse2.get(key).toString());


                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                list.add(new CurrencyModel(base, key, jsonResponse2.get(key).toString(), vv));
                                CurrencyAdapter adapter = new CurrencyAdapter(list, context);
                                recyclerView.setAdapter(adapter);
                                madapter = adapter;
                                recyclerView.setLayoutManager(new GridLayoutManager(context, 1));

                            }

                        } catch (Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(context, "Unexpected Error ", Toast.LENGTH_SHORT).show();
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
                    /* protected Map<String, String> getParams() {
//                ///Posting params to register url
                         Map<String, String> params = new HashMap<String, String>();
                         params.put("app_id", Strings.API_KEY1);
                         params.put("base", base);
                         return params;
                     }*/
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


