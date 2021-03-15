/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author jhonas
 */
public interface DAO<T> {
    void Save(T entity);
    T FindOne(Object filter);
    List<T> FindAll();
    void Update(T entity);
    void Delete(int id);
}