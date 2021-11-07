package edu.upc.dsa;


import edu.upc.dsa.models.Comanda;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuari;

import java.util.List;

public interface Manager {
    public List<Producto> ordenarProductosPrecio() throws EmptyList; //TEST FET
    public void realizarPedido(Comanda comanda); //TEST FET
    public Comanda servirPedido(); //TEST FET
    public List<Comanda> listadoPedidosUser(String usuariID) throws EmptyList; //TEST FET
    public List<Producto> ordenarProductosVentas() throws  EmptyList; //TEST FET
    public void añadirProductoLista(Producto producto);
    public void añadirUsuario(Usuari usuari);
    public Usuari getUsuari(String id);
    public int getNumProductos();
    public int getNumUsuarios();
}
