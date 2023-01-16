package com.beyza.bilcat;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onVaccinationClick(int position);
        void onRecentCommentsClick(int position);
        void onMapClick(int position);
        void onPingClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    Context context;

    ArrayList<CatData> list;

    public MyAdapter(Context context, ArrayList<CatData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.catlistcard, parent, false);
        return new MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CatData catData = list.get(position);
        holder.txtCatName.setText(catData.getName());
        holder.txtCatNighbourhood.setText(catData.getNeighbourhood());
        holder.txtCatAge.setText(catData.getAge());

        Glide.with(context).load(catData.getImage()).into(holder.imageViewCatPicture);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtCatName, txtCatNighbourhood, txtCatAge;
        Button buttonCatMap, buttonCatPing, buttonCatVaccination, buttonCatRecentComments;
        ImageView imageViewCatPicture;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            txtCatName = itemView.findViewById(R.id.txtCatName);
            txtCatNighbourhood = itemView.findViewById(R.id.txtCatNeighbourhood);
            txtCatAge = itemView.findViewById(R.id.txtCatAge);

            buttonCatMap = itemView.findViewById(R.id.ButtonCatMap);
            buttonCatPing = itemView.findViewById(R.id.ButtonCatPing);
            buttonCatVaccination = itemView.findViewById(R.id.ButtonCatVaccination);
            buttonCatRecentComments = itemView.findViewById(R.id.ButtonCatRecentComments);

            imageViewCatPicture = itemView.findViewById(R.id.ImageViewCatPicture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            buttonCatVaccination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onVaccinationClick(position);
                        }
                    }
                }
            });

            buttonCatRecentComments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onRecentCommentsClick(position);
                        }
                    }
                }
            });

            buttonCatMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onMapClick(position);
                        }
                    }
                }
            });

            buttonCatPing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onPingClick(position);
                        }
                    }
                }
            });


        }
    }
}
