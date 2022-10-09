package acevedo.EvalPar.org.Entidades;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import acevedo.EvalPar.org.R;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.ComentarioHolder> {
    List<Comentario> listaComentarios;

    public ComentarioAdapter(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    @NonNull
    @NotNull
    @Override

    public ComentarioAdapter.ComentarioHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.comentario_item, parent, false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);//para especificar el ancho y largo del item_pelicula
        vista.setLayoutParams(formaLayout);

        return new ComentarioHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ComentarioAdapter.ComentarioHolder holder, int position) {
        holder.txtComentario.setText(listaComentarios.get(position).getComentario());
        holder.ratCalificacionDB.setRating(listaComentarios.get(position).getCalificacion());
    }

    @Override
    public int getItemCount() {
        return listaComentarios.size();
    }


    public class ComentarioHolder extends RecyclerView.ViewHolder { //holder -> mantenedor
        TextView txtComentario;
        RatingBar ratCalificacionDB;
        public ComentarioHolder(@NonNull @NotNull View itemView) { //especifica que datos se mantienen para cada item
            super(itemView);
            //inicializar variables, donde se encuentran visualizadas
            txtComentario = itemView.findViewById(R.id.txtComentarioBD);
            ratCalificacionDB = itemView.findViewById(R.id.ratCaliDB);

        }
    }


}
