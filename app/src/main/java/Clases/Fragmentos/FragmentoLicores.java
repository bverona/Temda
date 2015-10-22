package Clases.Fragmentos;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adaptadores.AdaptadorLicor;
import Clases.Licor;
import codevperu.temda.R;

/**
 * Created by Bruno on 28/09/2015.
 */
public class FragmentoLicores extends FragmentoBase {

    private TextView descripcion;
    private TextView descuento;
    private TextView precioOriginal;
    private TextView precioVenta;
    private AdaptadorLicor adapter;
    private ArrayList<Licor> licores = new ArrayList<Licor>();
    private RecyclerView recyclerView;

    public FragmentoLicores(){
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeRequest();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstance){

        View view=  inflater.inflate(R.layout.fragmento_licores,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewLicores);
        adapter = new AdaptadorLicor(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                    Log.i("",jsonObject.getJSONArray("resultado").toString());
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

                    OnConnectionFinished();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.toString());
            }
        });
        addToQueue(request);
    }



}
