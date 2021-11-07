package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Usuari {

    List<Comanda> llistaComandesServides;
    String usuariID;
    double numcomandes;
    String nomusuari;

    public Usuari(){
        this.numcomandes=0;
        llistaComandesServides = new LinkedList<Comanda>();
    };

    public Usuari (String nomusuari, String usuariID) {
        this();
        this.setNomUsuari(nomusuari);
        this.setUsuariID(usuariID);
    }


    public String getNomUsuari(){

        return this.nomusuari;
    }
    public void setNomUsuari(String nom) {
        this.nomusuari = nom;
    }

    public String getUsuariID(){
        return this.usuariID;
    }

    public void setUsuariID(String usuariID){
        this.usuariID = usuariID;
    }

    public void afegirComanda(Comanda comanda){

        this.llistaComandesServides.add(comanda);
    }

    public List<Comanda> getLlistaComandesServides(){
        return this.llistaComandesServides;
    }

}
