package edu.upc.dsa;


import edu.upc.dsa.models.Comanda;
import edu.upc.dsa.models.ElementComanda;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuari;
import org.apache.log4j.Logger;

import java.util.*;

public class ManagerImpl implements Manager {

    private Queue<Comanda> misComandas;
    private List<Producto> listaProductos ;
    private HashMap<String, Usuari> usuaris;

    private static ManagerImpl instance;

    final static Logger logger = Logger.getLogger(ManagerImpl.class);

    private ManagerImpl(){
        misComandas = new LinkedList<Comanda>();
        listaProductos = new LinkedList<Producto>();
        usuaris = new HashMap<>();
    }

    public static ManagerImpl getInstance(){
        if (instance == null){
            instance = new ManagerImpl();
        }
        return instance;
    }

    public int getNumComandes() {
        return misComandas.size();
    }
    public int getNumProductos() { return listaProductos.size();}
    public int getNumUsuarios(){ return usuaris.size();}
    public List<Producto> getListaProductos() {
        return listaProductos;
    }
    public HashMap<String, Usuari> getUsuaris(){
        return usuaris;
    }

    public List<Producto> ordenarProductosPrecio(){
        List<Producto> listaOrdenada = this.listaProductos;
        Collections.sort(listaOrdenada, new OrdenarProductoPorPrecio());
        logger.info("Se han ordenado los productos por precio.");
        return listaOrdenada;
    }

    public void realizarPedido(Comanda comanda) {
        this.misComandas.add(comanda);
        logger.info("Comanda del usuario con ID "+comanda.getUsuariID()+" realizada!");
    }

    @Override
    public Comanda servirPedido() {
        Comanda comandaServida = misComandas.poll();
        if(comandaServida != null) {
            for (ElementComanda e : comandaServida.getLlistaCompra()) {
                for (Producto p : listaProductos) {
                    if (e.getProducto().equals(p.getNombre())) {
                        p.ventaRealizada(e.getQuantitat());
                        logger.info("Venta producto "+p.getNombre()+", cantidad "+ e.getQuantitat());
                    }
                }
            }

            String usuariID = comandaServida.getUsuariID();
            Usuari usuari = this.usuaris.get(usuariID);
            usuari.afegirComanda(comandaServida);
            logger.info("Pedido servido!");
        }
        else{
            logger.info("No hay mas pedidos para servir.");
        }
        return comandaServida;

    }



    @Override
    public List<Comanda> listadoPedidosUser(String usuariID) {
        return this.usuaris.get(usuariID).getLlistaComandesServides();
    }

    @Override
    public List<Producto> ordenarProductosVentas(){

        List<Producto> listaOrdenada = this.listaProductos;
        Collections.sort(listaOrdenada,new OrdenarProductoPerVentas().reversed());
        logger.info("Se han ordenado los productos por ventas.");
        return listaOrdenada;
    }

    @Override
    public void añadirProductoLista(Producto producto){
        this.listaProductos.add(producto);
        logger.info("Producto "+producto.getNombre()+" añadido.");
    }

    public void añadirUsuario(Usuari usuari){
        usuaris.put(usuari.getUsuariID(), usuari);
        logger.info("Usuario "+usuari.getNomUsuari()+" añadido.");
    }

    public Usuari getUsuari(String id){
        Usuari u = usuaris.get(id);
        return u;
    }

    public void borrarTot(){
        listaProductos.clear();
        misComandas.clear();
        usuaris.clear();
    }

}