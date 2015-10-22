package codevperu.temda;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adaptadores.AdaptadorOferta;
import Clases.Fragmentos.FragmentoHardware;
import Clases.Fragmentos.FragmentoLicores;
import Clases.Fragmentos.FragmentoPostres;
import Clases.Fragmentos.FragmentoRopa;
import Clases.Fragmentos.FragmentoSoftware;
import Clases.Fragmentos.FragmentoZapatos;
import Clases.Oferta;
import Clases.VolleyS;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private String nombre;
    private NetworkImageView foto;
    private ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
    private Toolbar toolbar;
    private RequestQueue fRequestQueue;
    private VolleyS volleyS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volleyS = VolleyS.getInstance(this);
        fRequestQueue = volleyS.getRequestQueue();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);

        //Indica la Toolbar a Utilizar
        toolbar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction= false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()){
                            case R.id.Licores:
                                Log.i("",menuItem.getItemId()+"");
                                fragment = new FragmentoLicores();
                                fragmentTransaction = true;
                                break;
                            case R.id.Postres:
                                fragment = new FragmentoPostres();
                                Log.i("",menuItem.getItemId()+"");
                                fragmentTransaction = true;
                                break;
                            case R.id.Zapatos:
                                fragment = new FragmentoZapatos();
                                Log.i("",menuItem.getItemId()+"");
                                fragmentTransaction = true;
                                break;
                            case R.id.Software:
                                Log.i("",menuItem.getItemId()+"");
                                fragment = new FragmentoSoftware();
                                fragmentTransaction = true;
                                break;
                            case R.id.Hardware:
                                Log.i("",menuItem.getItemId()+"");
                                fragment = new FragmentoHardware();
                                fragmentTransaction = true;
                                break;
                            case R.id.Ropa:
                                fragment = new FragmentoRopa();
                                Log.i("",menuItem.getItemId()+"");
                                fragmentTransaction = true;
                                break;
                        }
                        if(fragmentTransaction){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.FrameContenido,fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();
                        return true;
                    }
                }
        );


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPrincipal);

        makeRequest();

        AdaptadorOferta adaptadorOferta = new AdaptadorOferta(this,ofertas);


        recyclerView.setAdapter(adaptadorOferta);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        );
        //new GridLayoutManager(this,2)

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }


        return super.onOptionsItemSelected(item);
    }

    public void makeRequest(){
        String url ="http://www.codevperu.com/clasesW/WsImagenes.php";
        JsonObjectRequest request = new JsonObjectRequest(
                url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("resultado");
                    for (int i =0 ; i<jsonArray.length();i++)
                    {
                        Log.i("ID",""+i);
                        JSONObject objetoJson= (JSONObject) jsonArray.get(i);
                        Oferta oferta= new Oferta(
                                objetoJson.getString("descripcion"),
                                objetoJson.getString("imagen")
                        );
//                                "http://icons.iconarchive.com/icons/jonathan-rey/simpsons/256/Bart-Simpson-03-Scare-icon.png"
                        ofertas.add(oferta);
                    }
                    //OnConnectionFinished();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error","",error);
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



}
