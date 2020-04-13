/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author safratix
 */
public class RepasServi {
    private int idResto;
    private Timestamp date;
    
    
    public RepasServi(int idResto, Timestamp date){
        this.idResto = idResto;
        this.date = date;
    }
    
    
    public void setIdResto(int idResto){
        this.idResto = idResto;
    }
    
    public void setDate(Timestamp date){
        this.date = date;
    }
    
    public int getIdResto(){
        return idResto;
    }
    
    public Timestamp getDate(){
        return date;
    }
    
    @Override
    public String toString(){
        return "Repas servi le "+ date +"\n";
    }
    
    
}
