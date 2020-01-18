package com.caner.newproject.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.caner.newproject.R;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardsRecyclerAdapter extends RecyclerView.Adapter<CardsRecyclerAdapter.MyViewHolder> {
    private List<String> cards;

    public CardsRecyclerAdapter(List<String> cards) {
        this.cards = cards;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            byte[] bytes = Base64.getDecoder().decode(cards.get(position));
            Bitmap decodedByte = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            ((ImageView) holder.view.findViewById(R.id.card_image)).setImageBitmap(decodedByte);
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        View view;
        MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }
}
