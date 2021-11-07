package edu.upc.dsa.models;


import edu.upc.dsa.ManagerImpl;
import edu.upc.dsa.models.ElementComanda;
import edu.upc.dsa.models.Producto;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class Comanda {

    //private String usuari;
    private String usuariID;
    private List<ElementComanda> llistaCompra = new LinkedList<ElementComanda>();

    public Comanda(){};

    public Comanda(String usuariID, List<ElementComanda> llistaCompra) { // Constructor
        this.setUsuariID(usuariID);
        this.setLlistaCompra(llistaCompra);
    }

    public void setUsuariID(String usuariID){
        this.usuariID = usuariID;
    }

    public String getUsuariID() {
        return this.usuariID;
    }

    public List<ElementComanda> getLlistaCompra(){
        return this.llistaCompra;
    }

    public void setLlistaCompra(List<ElementComanda> llistaCompra){
        this.llistaCompra = llistaCompra;
    }


    public void addLP(int quantitat, String producto) {
        //producto.ventaRealizada(quantitat);
        //edu.upc.dsa.models.ElementComanda comanda = new edu.upc.dsa.models.ElementComanda(producto,quantitat);
        //this.llistaCompra.add(comanda);
        this.llistaCompra.add(new ElementComanda(producto, quantitat));


    }

}