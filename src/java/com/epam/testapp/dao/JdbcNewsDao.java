/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.dao;

import com.epam.testapp.connection.IConnectionPool;
import com.epam.testapp.connection.JdbcConnectionPool;
import com.epam.testapp.presentation.form.News;
import java.util.List;

/**
 *
 * @author Alena_Grouk
 */
public class JdbcNewsDao implements INewsDao{
    
    private IConnectionPool connectionPool;

    @Override
    public List getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(News news) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(List<Integer> idList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public News fetchById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the connectionPool
     */
    public IConnectionPool getConnectionPool() {
        return connectionPool;
    }

    /**
     * @param connectionPool the connectionPool to set
     */
    public void setConnectionPool(IConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
    
}
