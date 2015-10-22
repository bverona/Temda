package Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Clases.Licor;
import Clases.VolleyS;
import codevperu.temda.R;

/**
 * Created by Bruno on 30/09/2015.
 */
public class AdaptadorLicor
        extends RecyclerView.Adapter<AdaptadorLicor.LicorViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener listener;
    private ArrayList<Licor> licores = new ArrayList<Licor>();
    private static Context context;
    private RequestQueue fRequestQueue;
    private VolleyS volleyS;


    public AdaptadorLicor(Context context){
        this.context=context;
        volleyS = VolleyS.getInstance(context);
        fRequestQueue = volleyS.getRequestQueue();

        makeRequest();
    }


    @Override
    public LicorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_licores, parent, false);

        itemView.setOnClickListener(this);

        LicorViewHolder lvh = new LicorViewHolder(itemView);

        return lvh;

    }

    @Override
    public void onBindViewHolder(LicorViewHolder holder, int position) {

        Licor licor = licores.get(position);
        holder.BindLicor(licor);

    }

    @Override
    public int getItemCount() {
        return licores.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;
    }


    public void makeRequest(){
        String url ="http://www.codevperu.com/clasesW/WsListarProducto.php";
        JsonObjectRequest request = new JsonObjectRequest(
                url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("resultado");
                    for (int i =0 ; i<jsonArray.length();i++)
                    {
                        Log.i("", jsonObject.getJSONArray("resultado").toString());
                        JSONObject objetoJson= (JSONObject) jsonArray.get(i);

                        Licor licor= new Licor(
                                objetoJson.getInt("idproducto"),
                                objetoJson.getString("nombreproducto"),
                                objetoJson.getString("descripcion"),
                                objetoJson.getString("descuento"),
                                objetoJson.getString("precioreal"),
                                objetoJson.getString("preciodescontado"),
                                objetoJson.getString("imagen")
                        );
                        licores.add(licor);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error","Error en makeRequest");
            }
        });
        addToQueue(request);
    }


    public void addToQueue(Request request){

        if(request!=null){
            fRequestQueue = volleyS.getRequestQueue();
        }


//        onPreStartConection();
        fRequestQueue.add(request);
    }

    @Override
    public void onClick(View v) {

    }


    public class LicorViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre;
        private TextView descuento;
        private TextView precioOriginal;
        private TextView precioVenta;
        private ImageView imagen;

        public LicorViewHolder(View itemView) {
            super(itemView);


            nombre = (TextView) itemView.findViewById(R.id.txtDescripcionLicores);
            descuento = (TextView) itemView.findViewById(R.id.txtDescuentoLicores);
            precioOriginal = (TextView) itemView.findViewById(R.id.txtPrecioOriginalLicores);
            precioVenta = (TextView) itemView.findViewById(R.id.txtPrecioVentaLicores);
            imagen= (ImageView) itemView.findViewById(R.id.ImgLicores);

        }


        public void BindLicor(Licor l){

            Picasso.with(context)
                    .load(l.getImagen())
                    .placeholder(R.drawable.batisenhal)
                    .into(imagen);
            nombre.setText(l.getDescripcion());
            descuento.setText(l.getDescuento());
            precioOriginal.setText(l.getPrecioOriginal());
            precioVenta.setText(l.getPrecioVenta());


        }


    }

}
