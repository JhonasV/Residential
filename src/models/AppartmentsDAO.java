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
public class AppartmentsDAO {
      EntityManagerFactory emf;
    EntityManager em;
    public AppartmentsDAO() {
        emf = javax.persistence.Persistence.createEntityManagerFactory("ResidentialPU");
        em = emf.createEntityManager();
    }

    public void Save(Appartments user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();      
    }
    
    
    public Appartments FindOne(int filter){       
        return em.find(Appartments.class, filter);       
    }
    
    public Appartments FindOneByAppartmentName(String filter){       
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Appartments> criteria = builder.createQuery(Appartments.class);
        Root<Appartments> from = criteria.from(Appartments.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get(Appartments_.name), filter));
        TypedQuery<Appartments> typed = em.createQuery(criteria);
        
        try{
            return typed.getSingleResult();
        }catch(final NoResultException nre){
            return null;
        }
        
    }
   
   

    
    public List<Appartments> FindAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Appartments> cq = cb.createQuery(Appartments.class);
        Root<Appartments> rootEntry = cq.from(Appartments.class);
        cq.where(cb.isNull(rootEntry.get("isDeleted")));
        CriteriaQuery<Appartments> all = cq.select(rootEntry);
        TypedQuery<Appartments> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
    public void Update(Appartments entity) {
        Appartments oldEntity = this.FindOne(entity.getId());
        if(oldEntity != null){
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }else{
            try {
                throw new Exception("No se encontró información para actualizar.");
            } catch (Exception ex) {
                Logger.getLogger(AppartmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }

    public void Delete(int id) {
        Appartments user = this.FindOne(id);
        if(user != null){
            user.setIsDeleted(Boolean.TRUE);
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }else{
            try {
                throw new Exception("No se encontró información para eliminar.");
            } catch (Exception ex) {
                Logger.getLogger(AppartmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
}
