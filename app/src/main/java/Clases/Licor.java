package Clases;

import android.widget.ImageView;

/**
 * Created by Bruno on 23/09/2015.
 */
public class Licor {

    private int id;
    private String nombre;
    private String descripcion;
    private String descuento;
    private String precioOriginal;
    private String precioVenta;
    private String imagen;

    public Licor(int id, String nombre, String descripcion, String descuento, String precioOriginal, String precioVenta, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.precioOriginal = precioOriginal;
        this.precioVenta = precioVenta;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(String precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
