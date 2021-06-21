package com.example.myapplication.ui.ratelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Record;

import java.util.List;
import java.util.zip.Inflater;

public class RateListAdapter extends RecyclerView.Adapter<RateListAdapter.Holder> {
    LiveData<List<Record>> list;

    public RateListAdapter(LiveData<List<Record>> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_item, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Record record = list.getValue().get(position);

        holder.dateTextView.setText(record.getDate());
        holder.valueTextView.setText(record.getValue());
    }

    @Override
    public int getItemCount() {
        return list.getValue().size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView valueTextView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.rate_item_date);
            valueTextView = itemView.findViewById(R.id.rate_item_value);
        }
    }
}
