/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Pays;
import entities.Region;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Vadrama
 */
@Stateless
public class RegionFacade extends AbstractFacade<Region> implements RegionFacadeLocal {

    @PersistenceContext(unitName = "LABxPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegionFacade() {
        super(Region.class);
    }
    
    @Override
    public Integer nextId() throws Exception{
        try{
        Query query = em.createNamedQuery("Region.nextId");
        
        return ((Integer) query.getSingleResult()) + 1;
        }catch(NoResultException nre){
            return 1;
        }catch(NullPointerException npe){
            return 1;
        }
        catch(Exception e){
            throw new Exception(e);
        }
    }
    
    @Override
    public List<Region> findAll(){
        try {
            Query q = em.createNamedQuery("Region.findAll");
            q.setParameter("deleted", 0);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
