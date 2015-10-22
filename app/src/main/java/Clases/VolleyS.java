package Clases;

import android.content.Context;

import com.android.volley.RequestQueue;

/**
 * Created by Bruno on 25/09/2015.
 */
public class VolleyS {

    private static VolleyS mVolleyS = null;

    //cola donde se añadirán conexiones, para que la librería las realice
    private RequestQueue mRequestQueue;

    public VolleyS(Context context) {
        mRequestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
    }

    public static VolleyS getInstance(Context context){

        if(mVolleyS == null){
            mVolleyS = new VolleyS(context);
        }

        return mVolleyS;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

}
