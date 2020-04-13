/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISevices;

import Entities.Emplois;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author heshem
 */
public interface IServiceEmplois {
    
    
    
     void ajouter(Emplois t) throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean update(Emplois t,int id) throws SQLException;
    List<Emplois> readAll() throws SQLException;
    
}
