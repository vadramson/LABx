/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Ville;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Vadrama
 */
@Local
public interface VilleFacadeLocal {

    void create(Ville ville);

    void edit(Ville ville);

    void remove(Ville ville);

    Ville find(Object id);

    List<Ville> findAll();

    List<Ville> findRange(int[] range);

    int count();
    
    Integer next_Id() throws Exception;
    
}
