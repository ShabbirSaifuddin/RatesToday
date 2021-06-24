package com.staffshaw.ratestoday.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.staffshaw.ratestoday.Model.CurrencyModel;
import com.staffshaw.ratestoday.R;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> implements Filterable {

    List<CurrencyModel> list = new ArrayList<>();
    List<CurrencyModel> list1 = new ArrayList<>();
    TextView tvbase, tvkey;
    EditText etbase, etkey;
    Button btnconvet, btncompare;
    ImageButton btnclose;
    int check = 0;
    String checkbase = "";
    private Context mContext;

    public CurrencyAdapter(List<CurrencyModel> list, Context mContext) {
        this.list = list;
        this.list1 = list;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public CurrencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_currency, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CurrencyAdapter.ViewHolder holder, final int position) {

        holder.base.setText(list.get(position).getBASE());
        holder.key.setText(list.get(position).getKEY());
        holder.value.setText(list.get(position).getVALUE());
        holder.key2.setText(list.get(position).getKEY());
        holder.time.setText(list.get(position).getTIME());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Dialog topDialog = new Dialog(mContext, android.R.style.Theme_Translucent_NoTitleBar);
                topDialog.setContentView(R.layout.layout_dialog);
                topDialog.show();

                tvbase = topDialog.findViewById(R.id.tv_base_dialog);
                tvkey = topDialog.findViewById(R.id.tv_key_dialog);
                etbase = topDialog.findViewById(R.id.et_base_dialog);
                etkey = topDialog.findViewById(R.id.et_key_dialog);
                btncompare = topDialog.findViewById(R.id.btn_compare_dialog);
                btnconvet = topDialog.findViewById(R.id.btn_convert_dialog);
                btnclose = topDialog.findViewById(R.id.btn_close_dialog);

                tvbase.setText(list.get(position).getBASE());
                tvkey.setText(list.get(position).getKEY());

                btnclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvbase.setText(list.get(position).getBASE());
                        tvkey.setText(list.get(position).getKEY());
                        check = 0;
                        checkbase = tvbase.getText().toString();
                        topDialog.dismiss();
                    }
                });

                btncompare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        topDialog.dismiss();
//                        CurrencyFragment currencyFragment = new CurrencyFragment();
//                        currencyFragment.runfromadapter(list.get(position).getKEY(), mContext);

                        if (check == 0) {
                            tvkey.setText(list.get(position).getBASE());
                            tvbase.setText(list.get(position).getKEY());
                            check = 1;
                            checkbase = tvbase.getText().toString();

                        } else {
                            tvbase.setText(list.get(position).getBASE());
                            tvkey.setText(list.get(position).getKEY());
                            check = 0;
                            checkbase = tvbase.getText().toString();

                        }
                    }
                });

                //converter code


                btnconvet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (checkbase.equals("USD")) {
                            try {
                                final Double a = Double.valueOf(list.get(position).getVALUE());
                                final String base = etbase.getText().toString();

                                if (TextUtils.isEmpty(base)) {
                                    etbase.setError("Value Cannot Be Empty");
                                } else {
                                    Double b = Double.valueOf(base);
                                    Double ans = b * a;
                                    String Answer = String.format("%.2f", ans);
                                    etkey.setText(Answer);
                                }
                            } catch (Exception e) {
                                etbase.setError("Value Cannot Be Empty");
                            }
                        } else {
                            try {
                                final Double a = Double.valueOf(list.get(position).getVALUE());
                                final String base = etbase.getText().toString();

                                if (TextUtils.isEmpty(base)) {
                                    etbase.setError("Value Cannot Be Empty");
                                } else {
                                    Double b = Double.valueOf(base);
                                    Double ans = b / a;
                                    String Answer = String.format("%.4f", ans);
                                    etkey.setText(Answer);
                                }
                            } catch (Exception e) {
                                etbase.setError("Value Cannot Be Empty");
                            }
                        }


                    }
                });

                etbase.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() != 0) {
                            if (checkbase.equals("USD")) {
                                try {
                                    final Double a = Double.valueOf(list.get(position).getVALUE());
                                    final String base = etbase.getText().toString();

                                    if (TextUtils.isEmpty(base)) {
                                        etbase.setError("Value Cannot Be Empty");
                                    } else {
                                        Double b = Double.valueOf(base);
                                        Double ans = b * a;
                                        String Answer = String.format("%.2f", ans);
                                        etkey.setText(Answer);
                                    }
                                } catch (Exception e) {
                                    etbase.setError("Value Cannot Be Empty");
                                }
                            } else {
                                try {
                                    final Double a = Double.valueOf(list.get(position).getVALUE());
                                    final String base = etbase.getText().toString();

                                    if (TextUtils.isEmpty(base)) {
                                        etbase.setError("Value Cannot Be Empty");
                                    } else {
                                        Double b = Double.valueOf(base);
                                        Double ans = b / a;
                                        String Answer = String.format("%.4f", ans);
                                        etkey.setText(Answer);
                                    }
                                } catch (Exception e) {
                                    etbase.setError("Value Cannot Be Empty");
                                }
                            }
                        } else {
                            Toast.makeText(mContext, "Value Cannot Be Empty", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    list = list1;
                } else {

                    List<CurrencyModel> filterList = new ArrayList<>();

                    for (CurrencyModel data : list1) {

                        if (data.getKEY().contains(charString)) {
                            filterList.add(data);
                        }
                    }

                    list = filterList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                list = (List<CurrencyModel>) results.values;
                notifyDataSetChanged();
            }
        };

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView base, key, time, value, key2;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            base = itemView.findViewById(R.id.tv_base);
            key = itemView.findViewById(R.id.tv_key);
            time = itemView.findViewById(R.id.tv_date);
            value = itemView.findViewById(R.id.tv_value);
            key2 = itemView.findViewById(R.id.tv_Key2);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
