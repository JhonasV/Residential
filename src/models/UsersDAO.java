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
    public Users FindOne(Object filter){       
        return em.find(Users.class, filter);       
    }

    @Override
    public List<Users> FindAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Users> cq = cb.createQuery(Users.class);
        Root<Users> rootEntry = cq.from(Users.class);
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
                throw new Exception("No se encontró información para actualizar.");
            } catch (Exception ex) {
                Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }

    @Override
    public void Delete(int id) {
        Users user = this.FindOne(id);
        if(user != null){
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }else{
            try {
                throw new Exception("No se encontró información para eliminar.");
            } catch (Exception ex) {
                Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
}
