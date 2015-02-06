package com.epam.testapp.database.dao;

import com.epam.testapp.database.exception.DaoException;
import com.epam.testapp.model.News;
import java.util.List;

public interface INewsDao {
    public Integer insert(News news) throws DaoException;
    public void update(News news) throws DaoException;
    public void delete(List<News> news) throws DaoException;
    public List selectAll() throws DaoException;
    public News fetchById(News news) throws DaoException;
}
