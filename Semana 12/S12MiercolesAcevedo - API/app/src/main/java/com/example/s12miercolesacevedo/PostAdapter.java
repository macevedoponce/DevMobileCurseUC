package com.example.s12miercolesacevedo;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> implements View.OnClickListener{
    Context context;
    List<Item> postList;
    private View.OnClickListener listener;
    public PostAdapter(Context context , List<Item> postList){
        this.context = context;
        this.postList = postList;
    }
    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.eachpost , parent , false);
        mView.setOnClickListener(this);
        return new PostHolder(mView);
    }
    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Item item = postList.get(position);
        holder.setImageView(item.getImageUrlPreview());
        holder.setmTags(item.getTags());
        holder.setmLikes(item.getLikes());

    }


    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;

    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class PostHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView mLikes, mTags;
        View view;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
        public void setImageView(String url){
            imageView = view.findViewById(R.id.imageview);
            Glide.with(context).load(url).into(imageView);
        }
        public void setmLikes(int likes){
            mLikes = view.findViewById(R.id.likes);
            mLikes.setText(likes + " Likes");
        }
        public void setmTags(String tag){
            mTags = view.findViewById(R.id.tags);
            mTags.setText(tag);
        }
    }
}