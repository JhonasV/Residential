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
 * @author jhonas
 */
public class UsersDAO implements DAO<Users> {
    
    EntityManagerFactory emf;
    EntityManager em;
    public UsersDAO() {
        emf = javax.persistence.Persistence.createEntityManagerFactory("ResidentialPU");
        em = emf.createEntityManager();
    }

    @Override
    public void Save(Users user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();      
    }
    
    @Override
    public Users FindOne(int filter){       
        return em.find(Users.class, filter);       
    }
    
    public Users FindOneByUserName(Object filter){       
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Users> criteria = builder.createQuery(Users.class);
        Root<Users> from = criteria.from(Users.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get(Users_.userName), filter));
        TypedQuery<Users> typed = em.createQuery(criteria);
        
        try{
            return typed.getSingleResult();
        }catch(final NoResultException nre){
            return null;
        }
        
    }
    public Users FindOneByEmail(Object filter){       
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Users> criteria = builder.createQuery(Users.class);
        Root<Users> from = criteria.from(Users.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get(Users_.email), filter));
        TypedQuery<Users> typed = em.createQuery(criteria);
        
        try{
            return typed.getSingleResult();
        }catch(final NoResultException nre){
            return null;
        }
        
    }

    
    @Override
    public List<Users> FindAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Users> cq = cb.createQuery(Users.class);
        Root<Users> rootEntry = cq.from(Users.class);
        cq.where(cb.isNull(rootEntry.get("isDeleted")));
        CriteriaQuery<Users> all = cq.select(rootEntry);
        TypedQuery<Users> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
    @Override
    public void Update(Users entity) {
        Users oldEntity = this.FindOne(entity.getId());
        if(oldEntity != null){
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }else{
            try {
                throw new Exception("No se encontr?? informaci??n para actualizar.");
            } catch (Exception ex) {
                Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }

    @Override
    public void Delete(int id) {
        Users user = this.FindOne(id);
        if(user != null){
            user.setIsDeleted(Boolean.TRUE);
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }else{
            try {
                throw new Exception("No se encontr?? informaci??n para eliminar.");
            } catch (Exception ex) {
                Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
}
