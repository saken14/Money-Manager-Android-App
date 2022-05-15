package com.example.myapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.Element;

import java.util.List;

public class ElementAdapterMin extends RecyclerView.Adapter<ElementAdapterMin.ElementViewHolder> {

    Context context;
    List<Element> elements;

    public ElementAdapterMin(Context context, List<Element> elements) {
        this.context = context;
        this.elements = elements;
    }

    @NonNull
    @Override
    public ElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elementItems = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_item_minus, parent, false);
        return new ElementViewHolder(elementItems);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ElementViewHolder holder, int position) {
        holder.comment.setText(elements.get(position).getComment());
        holder.amount.setText(elements.get(position).getAmount()+"");
        holder.buy_date.setText(elements.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public static final class ElementViewHolder extends RecyclerView.ViewHolder {
        TextView comment;
        TextView amount;
        TextView buy_date;

        public ElementViewHolder(@NonNull View itemView) {
            super(itemView);

            comment = itemView.findViewById(R.id.comment_min);
            amount = itemView.findViewById(R.id.amount_min);
            buy_date = itemView.findViewById(R.id.buy_date_min);
        }
    }
}
