package Clases.Fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import Clases.VolleyS;

/**
 * Created by Bruno on 23/09/2015.
 */
public class FragmentoBase extends Fragment {

    private VolleyS volley ;
    protected RequestQueue fRequestQueue;

    public FragmentoBase(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volley= VolleyS.getInstance(getActivity().getApplicationContext());
        fRequestQueue= volley.getRequestQueue();
    }

    public  void onPreStartConection() {
        getActivity().setProgressBarIndeterminateVisibility(true);
    }

    public void OnConnectionFinished(){
        getActivity().setProgressBarIndeterminateVisibility(false);
    }

    public void onConnectionFailed(String error){
        getActivity().setProgressBarIndeterminateVisibility(false);
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT);
    }

    public void addToQueue(Request request){

        if(request!=null){
            fRequestQueue = volley.getRequestQueue();
        }


//        onPreStartConection();
        fRequestQueue.add(request);
    }

}
