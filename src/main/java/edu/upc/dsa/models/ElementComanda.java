package edu.upc.dsa.models;

public class ElementComanda {

    private String producto;
    private int quantitat;

    public ElementComanda(){};

    public ElementComanda(String producto, int quantitat){
        this();
        this.setProducto(producto);
        this.setQuantitat(quantitat);
    }

    public String getProducto(){
        return this.producto;
    }

    public int getQuantitat(){
        return this.quantitat;
    }

    public void setProducto(String producto){
        this.producto = producto;
    }

    public void setQuantitat(int quantitat){
        this.quantitat = quantitat;
    }

}
