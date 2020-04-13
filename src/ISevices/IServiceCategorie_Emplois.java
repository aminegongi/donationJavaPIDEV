/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISevices;

import Entities.Categorie_Emplois;
import Entities.Emplois;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author heshem
 */
public interface IServiceCategorie_Emplois {
    void ajouterCategorieEmplois(Categorie_Emplois t) throws SQLException;
    boolean deleteCategorieEmplois(int id) throws SQLException;
    boolean updateCategorieEmplois(Categorie_Emplois t,int id) throws SQLException;
    List<Categorie_Emplois> readAllCategorieEmplois() throws SQLException;
}
