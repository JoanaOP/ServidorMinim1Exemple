package edu.upc.dsa.services;


import edu.upc.dsa.EmptyList;
import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;
import edu.upc.dsa.models.Comanda;
import edu.upc.dsa.models.ElementComanda;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuari;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/productos", description = "Endpoint to Productos Service")
@Path("/productos")
public class ProductsService {

    private Manager manager;

    public ProductsService() {
        this.manager = ManagerImpl.getInstance();

        if(manager.getNumProductos() == 0){
            manager.añadirProductoLista(new Producto("Cafe",0.8));
            manager.añadirProductoLista(new Producto("Cheese Cake",2.5));
            manager.añadirProductoLista(new Producto("Croissant",1.2));
            manager.añadirProductoLista(new Producto("Suc de taronja",4.1));
            manager.añadirProductoLista(new Producto("Donut",1.30));
        }
        if(manager.getNumUsuarios() == 0){
            manager.añadirUsuario(new Usuari("Joana","X"));
            manager.añadirUsuario(new Usuari("Jordi","33333333Y"));
            manager.añadirUsuario(new Usuari("Aida","11111111Z"));
            List<ElementComanda> llista = new LinkedList<ElementComanda>();
            llista.add(new ElementComanda("Donut",3));
            llista.add(new ElementComanda("Cafe",1));
            Comanda comanda = new Comanda("X", llista);
            manager.realizarPedido(comanda);
        }

    }

    @GET
    @ApiOperation(value = "get Productos Ordenados por precio", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/Precio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosOrdenadosPrecio() throws EmptyList {

        List<Producto> productosOrdenadosPrecio = manager.ordenarProductosPrecio();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productosOrdenadosPrecio) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get Productos Ordenados por ventas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/Ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosOrdenadosVentas() throws EmptyList {

        List<Producto> productosOrdenadosVentas = manager.ordenarProductosVentas();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productosOrdenadosVentas) {};
        return Response.status(201).entity(entity).build();

    }


    @GET
    @ApiOperation(value = "get Productos Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Comanda.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosUsuario(@PathParam("id") String id) throws EmptyList {
        Usuari usuari = manager.getUsuari(id);
        if (usuari == null){
            return Response.status(404).build();
        }
        else{
            List<Comanda> comandaUsuario = manager.listadoPedidosUser(id);
            GenericEntity<List<Comanda>> entity = new GenericEntity<List<Comanda>>(comandaUsuario) {};
            return Response.status(201).entity(entity).build();
        }
    }

    @GET
    @ApiOperation(value = "servir pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Comanda.class),
            @ApiResponse(code = 404, message = "No hay mas comandas")
    })
    @Path("/servir")
    @Produces(MediaType.APPLICATION_JSON)
    public Response servirPedido() {
        Comanda comandaServida = manager.servirPedido();
        if (comandaServida == null) return Response.status(404).build();
        else  return Response.status(201).entity(comandaServida).build();
    }

    @POST
    @ApiOperation(value = "realizar pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Comanda.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/Pedido")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response realizarPedido(Comanda comanda) {

        int error = 0;
        if(comanda.getUsuariID()==null || comanda.getLlistaCompra().size()==0){
            error = 1;
        }
        else{

            for(ElementComanda e: comanda.getLlistaCompra()){
                if(e.getProducto() == null){
                    error = 1;
                }
            }

        }

        if(error == 1){
            return Response.status(500).entity(comanda).build();
        }
        else{

            this.manager.realizarPedido(comanda);
            return Response.status(201).entity(comanda).build();
        }

    }

/*
    @DELETE
    @ApiOperation(value = "delete a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        Track t = this.manager.getTrack(id);
        if (t == null) return Response.status(404).build();
        else this.manager.deleteTrack(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {

        Track t = this.manager.updateTrack(track);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }




 */


}