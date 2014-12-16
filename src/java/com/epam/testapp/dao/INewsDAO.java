/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.dao;

import com.epam.testapp.presentation.form.News;
import java.util.List;

/**
 *
 * @author Alena_Grouk
 */
public interface INewsDAO {
    public List getList();
    public void save(News news);
    public void remove(News news);
    public News fetchById(Integer id);
    
}
