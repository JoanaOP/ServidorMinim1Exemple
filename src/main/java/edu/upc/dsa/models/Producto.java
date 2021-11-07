package edu.upc.dsa.models;

public class Producto{

    private String nombre;
    private double precio;
    private double ventas;

    public Producto(){};

    public Producto(String nombre, double precio) {
        this();
        this.setPrecio(precio);
        this.setNombre(nombre);
        this.setVentas(0);
    }

    public String getNombre(){
        return this.nombre;
    }

    public void ventaRealizada(int numero){
        this.ventas = this.ventas + numero;
    }

    public double getPrecio(){
        return this.precio;
    }
    public int compareTo(Producto a)
    {
        int res = (int) (this.getPrecio()-a.getPrecio());
        return res;
    }

    public double getVentas() {
        return ventas;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setVentas(double ventas){
        this.ventas = ventas;
    }

}
