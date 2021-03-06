package com.example.manga.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.ChapterActivity;
import com.example.manga.R;
import com.example.manga.common.Common;
import com.example.manga.interfaces.IRecyclerItemClickListener;
import com.example.manga.model.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyComicAdapter extends RecyclerView.Adapter<MyComicAdapter.MyViewHolder> {

    private Context context;
    private List<Comic> comicList;
    private LayoutInflater inflater;

    public MyComicAdapter(Context context, List<Comic> comicList) {
        this.context = context;
        this.comicList = comicList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyComicAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_comic, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyComicAdapter.MyViewHolder myViewHolder, int i) {
        Picasso.get().load(comicList.get(i).Image).into(myViewHolder.imageComic);
        myViewHolder.textComicName.setText(comicList.get(i).Name);

        // Event
        myViewHolder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Save comic selected
                Common.comicSelected = comicList.get(position);
                context.startActivity(new Intent(context, ChapterActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageComic;
        private TextView textComicName;

        private IRecyclerItemClickListener recyclerItemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageComic = itemView.findViewById(R.id.image_comic);
            textComicName = itemView.findViewById(R.id.text_comic_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerItemClickListener.onClick(view, getAdapterPosition());
        }

        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener){
            this.recyclerItemClickListener = recyclerItemClickListener;
        }
    }
}
