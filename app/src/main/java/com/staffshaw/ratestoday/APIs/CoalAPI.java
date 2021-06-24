package com.staffshaw.ratestoday.APIs;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.staffshaw.ratestoday.Connection.Connection;
import com.staffshaw.ratestoday.Model.CoalModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CoalAPI {

    List<CoalModel> list = new ArrayList<>();

    public void getCoal(final Context context, final LinearLayout linearLayout, final TextView name, final TextView source, final TextView price, final TextView date, final ProgressBar progressBar) {

        list.clear();
        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);

        if (Connection.isNetworkAvailable(context)) {

            String URL = "https://www.quandl.com/api/v3/datasets/ODA/PCOALAU_USD.json?order=desc&column_index=1&api_key=zQethT3ZviT2xz34q3y5&limit=1";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonResponse = new JSONObject(response).getJSONObject("dataset");

                        jsonResponse.optString("database_code");
                        jsonResponse.optString("name");
                        jsonResponse.optString("description");
                        String abc = jsonResponse.optString("data");

                        abc = abc.replaceAll("\\[", "").replaceAll("]", "");

                        String[] separated = abc.split(",");
                        String date1 = separated[0].trim(); // this will contain "Fruit"
                        String price1 = separated[1].trim();
                        date1 = date1.replaceAll("\"", "");
                        String name1 = jsonResponse.optString("name");
                        String name2 = name1.substring(0, 29);
                        price1 = price1.substring(0, 5);


                        Log.d("Gold API", "onResponse: " + jsonResponse.optString("database_code") + " :" + jsonResponse.optString("name") + " :" + jsonResponse.optString("description") + " :" + date + "  :" + price);

                        list.add(new CoalModel(name2, price1, date1, jsonResponse.optString("database_code"), jsonResponse.optString("description")));

                        CoalModel.Name.add(name2);
                        CoalModel.Value.add(price1);
                        CoalModel.Time.add(date1);
                        CoalModel.Source.add(jsonResponse.optString("database_code"));
                        CoalModel.Description.add(jsonResponse.optString("description"));

                        progressBar.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                        name.setText(name2);
                        source.setText(jsonResponse.optString("database_code"));
                        price.setText("$ " + price1);
                        date.setText(date1);


                    } catch (Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "Unexpected Error ", Toast.LENGTH_SHORT).show();

                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                }
            }) {
            };

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context, "No internet Connection", Toast.LENGTH_SHORT).show();

        }

    }

}
