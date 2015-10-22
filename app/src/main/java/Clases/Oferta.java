package Clases;

import android.graphics.Bitmap;

/**
 * Created by Bruno on 23/09/2015.
 */
public class Oferta {

    private String nombre;
    private String imagen;

    public Oferta(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
