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
public class TarifResto {
    private int idResto;
    private String nomResto;
    private float tarif;
    private float portefeuilleVirtuel;
    
    public TarifResto(int idResto, float tarif){
        this.idResto = idResto;
        this.tarif = tarif;
    }
    
     public TarifResto(int idResto, float tarif, float portefeuilleVirtuel){
        this.idResto = idResto;
        this.tarif = tarif;
        this.portefeuilleVirtuel = portefeuilleVirtuel;
    }
     
     public TarifResto(int idResto, String nomResto, float tarif, float portefeuilleVirtuel){
        this.idResto = idResto;
        this.nomResto = nomResto;
        this.tarif = tarif;
        this.portefeuilleVirtuel = portefeuilleVirtuel;
    }
    
    public TarifResto(float tarif){
        this.tarif = tarif;
    }
    
    public void setIdResto(int idResto){
        this.idResto = idResto;
    }
    
   public void setNomResto(String nomResto){
        this.nomResto = nomResto;
    } 
    
    public void setTarif(float tarif){
        this.tarif = tarif;
    }
    
    public int getIdResto(){
        return idResto;
    }
    
     public String getNomResto(){
        return nomResto;
    }
    public float getTarif(){
        return tarif;
    }
    
    @Override
    public String toString(){
        return "Restaurant : "+ nomResto +", Tarif : "+ tarif + ", PortefeuilleVirtuel : " + portefeuilleVirtuel + "\n" ; 
    }
    
    
}
