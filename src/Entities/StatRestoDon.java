/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author safratix
 */
public class StatRestoDon {
    private int idResto;
    private String nomResto;
    private int nbrRepasServi;

    public StatRestoDon(int idResto, String nomResto, int nbrRepasServi) {
        this.idResto = idResto;
        this.nomResto = nomResto;
        this.nbrRepasServi = nbrRepasServi;
    }

    public int getIdResto() {
        return idResto;
    }

    public void setIdResto(int idResto) {
        this.idResto = idResto;
    }

    public String getNomResto() {
        return nomResto;
    }

    public void setNomResto(String nomResto) {
        this.nomResto = nomResto;
    }

    public int getNbrRepasServi() {
        return nbrRepasServi;
    }

    public void setNbrRepasServi(int nbrRepasServi) {
        this.nbrRepasServi = nbrRepasServi;
    }
    
    
    
}
