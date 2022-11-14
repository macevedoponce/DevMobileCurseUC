package com.acevedo.s12acevedo;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaHolder> implements View.OnClickListener {
    Context context;
    List<Pelicula> peliculaList;
    private View.OnClickListener listener;

    public PeliculaAdapter(Context context, List<Pelicula> peliculaList) {
        this.context = context;
        this.peliculaList = peliculaList;
    }

    @NonNull
    @NotNull
    @Override
    public PeliculaAdapter.PeliculaHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.item_post , parent , false);
        mView.setOnClickListener(this);
        return new PeliculaHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PeliculaAdapter.PeliculaHolder holder, int position) {
        Pelicula item = peliculaList.get(position);
        holder.setRuta_imagen(item.getRuta_imagen());
        //holder.setTitle(item.getTitle());

    }


    @Override
    public int getItemCount() {
        return peliculaList.size();
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

    public class PeliculaHolder extends RecyclerView.ViewHolder {
        ImageView imgPortada;
        TextView txtTitulo;
        View view;
        public PeliculaHolder(@NotNull View itemView) {
            super(itemView);
            view = itemView;
        }
        public void setRuta_imagen(String ruta_imagen){
            imgPortada = view.findViewById(R.id.imgPortada);
            Glide.with(context).load(ruta_imagen).into(imgPortada);
        }
        public void setTitle(String title){
            //txtTitulo = view.findViewById(R.id.txtTitulo);
            //txtTitulo.setText(title);
        }
    }
}
