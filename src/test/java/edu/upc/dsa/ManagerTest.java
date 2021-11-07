package edu.upc.dsa;

import edu.upc.dsa.models.Comanda;
import edu.upc.dsa.models.ElementComanda;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuari;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ManagerTest {

    ManagerImpl manager = ManagerImpl.getInstance();
    Producto cafe;
    Producto cheesecake;
    Producto croissant;
    Producto sucdetaronja;
    Producto donut;

    Comanda comanda1;
    Comanda comanda2;


    @Before
    public void setUp  () {
        manager.añadirUsuario(new Usuari("Joana","22222222X"));
        manager.añadirUsuario(new Usuari("Jordi","33333333Y"));
        manager.añadirUsuario(new Usuari("Aida","11111111Z"));

        cafe = new Producto("Cafe",0.8);
        cheesecake = new Producto("Cheese Cake",2.5);
        croissant = new Producto("Croissant",1.2);
        sucdetaronja = new Producto("Suc de taronja",4.1);
        donut = new Producto("Donut",1.30);

        manager.añadirProductoLista(cafe);
        manager.añadirProductoLista(cheesecake);
        manager.añadirProductoLista(croissant);
        manager.añadirProductoLista(sucdetaronja);
        manager.añadirProductoLista(donut);

        comanda1 = new Comanda("11111111Z", new LinkedList<ElementComanda>());
        comanda1.addLP(2, "Donut");
        comanda1.addLP(1, "Cafe");
        comanda1.addLP(4, "Croissant");
        manager.realizarPedido(comanda1);


        comanda2 = new Comanda("22222222X", new LinkedList<ElementComanda>());
        comanda2.addLP(1, "Croissant");
        comanda2.addLP(3, "Cheese Cake");
        comanda2.addLP(2, "Suc de taronja");
        comanda2.addLP(1, "Donut");
        manager.realizarPedido(comanda2);

        manager.servirPedido();

    }

    @Test
    public void probaRealizarPedido() {

        Assert.assertEquals(1,manager.getNumComandes());
        manager.realizarPedido(comanda1);
        Assert.assertEquals(2,manager.getNumComandes());
    }
    @Test
    public void probaServirPedido() {

        manager.realizarPedido(comanda1);
        manager.servirPedido();
        Assert.assertEquals(1, manager.getNumComandes());
        manager.servirPedido();
        Assert.assertEquals(0, manager.getNumComandes());
    }
    @Test
    public void probaListadoPedidosUser() {

        Assert.assertEquals("Donut",manager.listadoPedidosUser("11111111Z").get(0).getLlistaCompra().get(0).getProducto());
        Assert.assertEquals(2,manager.listadoPedidosUser("11111111Z").get(0).getLlistaCompra().get(0).getQuantitat());
        Assert.assertEquals("Cafe",manager.listadoPedidosUser("11111111Z").get(0).getLlistaCompra().get(1).getProducto());
        Assert.assertEquals(1,manager.listadoPedidosUser("11111111Z").get(0).getLlistaCompra().get(1).getQuantitat());
        Assert.assertEquals("Croissant",manager.listadoPedidosUser("11111111Z").get(0).getLlistaCompra().get(2).getProducto());
        Assert.assertEquals(4,manager.listadoPedidosUser("11111111Z").get(0).getLlistaCompra().get(2).getQuantitat());
    }
    @Test
    public void probaOrdenarProductosPrecio() {
        List<Producto> misProductos = new LinkedList<>();
        misProductos.add(cafe);
        misProductos.add(croissant);
        misProductos.add(donut);
        misProductos.add(cheesecake);
        misProductos.add(sucdetaronja);
        Assert.assertEquals(misProductos, manager.ordenarProductosPrecio());
    }
    @Test
    public void probaOrdenarProductosVentas() {
        List<Producto> misProductos = new LinkedList<>();
        misProductos.add(croissant);
        misProductos.add(donut);
        misProductos.add(cafe);
        misProductos.add(cheesecake);
        misProductos.add(sucdetaronja);
        Assert.assertEquals(misProductos, manager.ordenarProductosVentas());
    }
    @Test
    public void probaAñadirProductoLista(){
        Producto cacaolat = new Producto("Cacaolat",1);
        manager.añadirProductoLista(cacaolat);
        Assert.assertEquals(6,manager.getNumProductos());
        Assert.assertEquals(cacaolat,manager.getListaProductos().get(manager.getNumProductos()-1));
    }

    @Test
    public void probaAñadirUsuario(){
        manager.añadirUsuario(new Usuari("Toni","55555555T"));
        Assert.assertEquals(4,manager.getNumUsuarios());
        Assert.assertEquals("Toni",manager.getUsuaris().get("55555555T").getNomUsuari());
    }

    @After
    public void tearDown(){

        manager.borrarTot();

        cafe = null;
        cheesecake = null;
        croissant = null;
        sucdetaronja = null;
        donut = null;

        comanda1 = null;
        comanda2 = null;
    }


}
