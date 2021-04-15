/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author EnriqueCedeno
 */
public class BuildingsDAO {
    EntityManagerFactory emf;
    EntityManager em;
    public BuildingsDAO() {
        emf = javax.persistence.Persistence.createEntityManagerFactory("ResidentialPU");
        em = emf.createEntityManager();
    }

    public void Save(Buildings user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();      
    }
    
    
    public Buildings FindOne(int filter){       
        return em.find(Buildings.class, filter);       
    }
    
    public Buildings FindOneByBuildingName(String filter){       
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Buildings> criteria = builder.createQuery(Buildings.class);
        Root<Buildings> from = criteria.from(Buildings.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get(Buildings_.name), filter));
        TypedQuery<Buildings> typed = em.createQuery(criteria);
        
        try{
            return typed.getSingleResult();
        }catch(final NoResultException nre){
            return null;
        }
        
    }
   
   

    
    public List<Buildings> FindAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Buildings> cq = cb.createQuery(Buildings.class);
        Root<Buildings> rootEntry = cq.from(Buildings.class);
        cq.where(cb.isNull(rootEntry.get("isDeleted")));
        CriteriaQuery<Buildings> all = cq.select(rootEntry);
        TypedQuery<Buildings> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
    public void Update(Buildings entity) {
        Buildings oldEntity = this.FindOne(entity.getId());
        if(oldEntity != null){
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }else{
            try {
                throw new Exception("No se encontró información para actualizar.");
            } catch (Exception ex) {
                Logger.getLogger(BuildingsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }

    public void Delete(int id) {
        Buildings user = this.FindOne(id);
        if(user != null){
            user.setIsDeleted(Boolean.TRUE);
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }else{
            try {
                throw new Exception("No se encontró información para eliminar.");
            } catch (Exception ex) {
                Logger.getLogger(BuildingsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
}
