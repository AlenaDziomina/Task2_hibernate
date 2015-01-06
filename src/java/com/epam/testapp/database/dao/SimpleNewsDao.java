/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.dao;

import com.epam.testapp.model.News;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alena_Grouk
 */
public class SimpleNewsDao implements INewsDao {

    private final List<News> newsList = new ArrayList();
    private int index = 0;
    
    @Override
    public List getList() {
        return newsList;
    }

    @Override
    public Integer save(News news) {
        if (news == null) return null;
        if (news.getId() == null) {
            news.setId(++index);
        }
        newsList.add(news);
        return index;
    }

    @Override
    public void remove(List<Integer> idList) {
        newsList.clear();
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
