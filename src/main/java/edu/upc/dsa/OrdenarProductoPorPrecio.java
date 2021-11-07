package edu.upc.dsa;

import edu.upc.dsa.models.Producto;

import java.util.Comparator;

public class OrdenarProductoPorPrecio implements Comparator<Producto> {

    public int compare(Producto p1, Producto p2){

        return (int)(p1.getPrecio() - p2.getPrecio());

    }

}