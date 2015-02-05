package com.epam.testapp.service;

import com.epam.testapp.database.exception.DaoException;
import com.epam.testapp.model.News;
import java.util.List;

public interface INewsService {
    public List getList() throws DaoException;
    public Integer save(News news) throws DaoException;
    public void remove(List<Integer> idList) throws DaoException;
    public News fetchById(Integer id) throws DaoException;
}
