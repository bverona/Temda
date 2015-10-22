package Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Clases.Oferta;
import codevperu.temda.R;

/**
 * Created by Bruno on 23/09/2015.
 */
public class  AdaptadorOferta
        extends RecyclerView.Adapter<AdaptadorOferta.OfertaViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener listener;
    private ArrayList<Oferta>  ofertas;
    private static Context context;

    public AdaptadorOferta( Context context,ArrayList<Oferta> ofertas) {

        this.ofertas = ofertas;
        this.context= context;
    }

    @Override
    public OfertaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_oferta,viewGroup,false);

        itemView.setOnClickListener(this);

        OfertaViewHolder ovh = new OfertaViewHolder(itemView);

        return ovh;
    }

    @Override
    public void onBindViewHolder(OfertaViewHolder holder, int i) {

        Oferta oferta = ofertas.get(i);
        holder.bindOferta(oferta);

    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }


        @Override
        public int getItemCount() {
            return ofertas.size();
        }


        @Override
        public void onClick(View v) {

        }

        public static class OfertaViewHolder extends RecyclerView.ViewHolder
        {
            ImageView image;

            public OfertaViewHolder(View itemView) {
                super(itemView);

                image = (ImageView) itemView.findViewById(R.id.imgInicio);
            }

            public void bindOferta(Oferta o)
            {
                Picasso.with(context)
                        .load(o.getImagen())
                        .placeholder(R.drawable.lois)
                        .into(image);
                image.setContentDescription(o.getNombre());
            }

    }


}