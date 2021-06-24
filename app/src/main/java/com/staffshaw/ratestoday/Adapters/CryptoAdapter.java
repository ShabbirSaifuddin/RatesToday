package com.staffshaw.ratestoday.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.staffshaw.ratestoday.Model.CryptoModel;
import com.staffshaw.ratestoday.R;

import java.util.ArrayList;
import java.util.List;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.ViewHolder> implements Filterable {

    List<CryptoModel> list = new ArrayList<>();
    List<CryptoModel> list1 = new ArrayList<>();
    private Context mContext;

    public CryptoAdapter(List<CryptoModel> list, Context mContext) {
        this.list = list;
        this.list1 = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CryptoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_crypto, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoAdapter.ViewHolder holder, final int position) {


        holder.base.setText(list.get(position).getNAME());
        holder.key.setText(list.get(position).getKEY());
        holder.value.setText(list.get(position).getPRICE());
        holder.rank.setText(list.get(position).getRANK());
        holder.time.setText(list.get(position).getTIME());
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

                    List<CryptoModel> filterList = new ArrayList<>();

                    for (CryptoModel data : list1) {
                        ///TODO: CHANGE HERE ADD ANOTHER CONDITION
                        if (data.getKEY().contains(charString) || data.getNAME().toLowerCase().contains(charString)) {
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

                list = (List<CryptoModel>) results.values;
                notifyDataSetChanged();
            }
        };

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView base, key, time, value, rank;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            base = itemView.findViewById(R.id.tv_base);
            key = itemView.findViewById(R.id.tv_key);
            time = itemView.findViewById(R.id.tv_date);
            value = itemView.findViewById(R.id.tv_value);
            rank = itemView.findViewById(R.id.tv_rank);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
