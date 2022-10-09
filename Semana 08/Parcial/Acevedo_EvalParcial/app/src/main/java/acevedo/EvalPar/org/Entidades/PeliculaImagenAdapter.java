package acevedo.EvalPar.org.Entidades;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import acevedo.EvalPar.org.R;
import acevedo.EvalPar.org.ui.comentario.ComentarioPelicula;
import acevedo.EvalPar.org.ui.consultar.ConsultarComentariosPelicula;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PeliculaImagenAdapter extends RecyclerView.Adapter<PeliculaImagenAdapter.PeliculaHolder> {
    List<Pelicula> listaPeliculas; // una lista de tipo pelicula



    public PeliculaImagenAdapter(List<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    @NonNull
    @NotNull
    @Override
    public PeliculaHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula_item, parent, false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);//para especificar el ancho y largo del item_pelicula
        vista.setLayoutParams(formaLayout);

        return new PeliculaHolder(vista); // devolver vista moldeandolo a la vista pelicula_item

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PeliculaHolder holder, int position) {
    // por cada elemento que recibimos vamos a enlazar con el holder
        holder.txtTitle.setText(listaPeliculas.get(position).getTitle());
        holder.txtDescription.setText(listaPeliculas.get(position).getDescription());
        holder.txtTime.setText(listaPeliculas.get(position).getTime());
        holder.txtYear.setText(listaPeliculas.get(position).getYear());
        holder.txtDirector.setText(listaPeliculas.get(position).getDirector());
        holder.txtCategory.setText(listaPeliculas.get(position).getCategory());
        if(listaPeliculas.get(position).getImagen() != null){
            holder.imgPelicula.setImageBitmap(listaPeliculas.get(position).getImagen());
        }else{
            holder.imgPelicula.setImageResource(R.drawable.img_base);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(view.getContext(), "pelicula = "+position, Toast.LENGTH_SHORT).show();
                Intent i= new Intent(holder.itemView.getContext(), ConsultarComentariosPelicula.class);
                i.putExtra("idPelicula",listaPeliculas.get(position).getId());
                i.putExtra("title",listaPeliculas.get(position).getTitle());
                i.putExtra("img",listaPeliculas.get(position).getImagen());
                holder.itemView.getContext().startActivity(i);

            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View vista) {
//
//                Toast.makeText(vista.getContext(), "pelicula = ", Toast.LENGTH_SHORT).show();
////                Intent i= new Intent(holder.itemView.getContext(), ConsultarComentariosPelicula.class);
////                i.putExtra("idPelicula",idPelicula);
////                holder.itemView.getContext().startActivity(i);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size(); // devolvemos el numero de filas
    }

    public class PeliculaHolder extends RecyclerView.ViewHolder { //holder -> mantenedor
        TextView txtTitle, txtDescription, txtTime, txtYear, txtDirector, txtCategory;
        ImageView imgPelicula;
        public PeliculaHolder(@NonNull @NotNull View itemView) { //especifica que datos se mantienen para cada item
            super(itemView);
            //inicializar variables, donde se encuentran visualizadas
            txtTitle = itemView.findViewById(R.id.txtTitlePelicula);
            txtDescription = itemView.findViewById(R.id.txtDescriptionPelicula);
            txtTime = itemView.findViewById(R.id.txtTimePelicula);
            txtYear = itemView.findViewById(R.id.txtYearPelicula);
            txtDirector = itemView.findViewById(R.id.txtDirectorPelicula);
            txtCategory = itemView.findViewById(R.id.txtCategoryPelicula);
            imgPelicula = itemView.findViewById(R.id.imgImagenPelicula);

        }
    }
}
