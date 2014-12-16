/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.dao;

import com.epam.testapp.presentation.form.News;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alena_Grouk
 */
public class NewsDAO implements INewsDAO {

    private final List<News> newsList = new ArrayList();
    private int index = 0;
    
    @Override
    public List getList() {
        return newsList;
    }

    @Override
    public void save(News news) {
        if (news == null) return;
        if (news.getId() == null) {
            news.setId(++index);
        }
        newsList.add(news);
    }

    @Override
    public void remove(News news) {
        newsList.remove(news);
    }

    @Override
    public News fetchById(Integer id) {
        for (News news : newsList) {
            if (news.getId() == id) {
                return (News) news;
            }
        }
        return null;
    }
    
}
