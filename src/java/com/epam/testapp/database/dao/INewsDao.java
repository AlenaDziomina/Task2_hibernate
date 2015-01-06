/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.dao;

import com.epam.testapp.database.exception.DaoException;
import com.epam.testapp.model.News;
import java.util.List;

/**
 *
 * @author Alena_Grouk
 */
public interface INewsDao {
    public List getList() throws DaoException;
    public Integer save(News news) throws DaoException;
    public void remove(List<Integer> idList) throws DaoException;
    public News fetchById(Integer id) throws DaoException;
    
}
