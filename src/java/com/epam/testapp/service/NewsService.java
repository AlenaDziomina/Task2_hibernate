package com.epam.testapp.service;

import com.epam.testapp.database.dao.INewsDao;
import com.epam.testapp.database.exception.DaoException;
import com.epam.testapp.model.News;
import java.util.ArrayList;
import java.util.List;

public class NewsService implements INewsService {
    
    private INewsDao newsDao;
    
    @Override
    public List getList() throws DaoException {
        List newsList = getNewsDao().select(null);
        return newsList;
    }

    @Override
    public Integer save(News news) throws DaoException {
        Integer id = news.getId();
        if (id != null && id > 0){
            getNewsDao().update(news);
        } else {
            id = getNewsDao().insert(news);
        }
        return id;
    }

    @Override
    public void remove(List<Integer> idList) throws DaoException {
        getNewsDao().delete(idList);
    }

    @Override
    public News fetchById(Integer id) throws DaoException {
        News news = new News();
        news.setId(id);
        List params = new ArrayList();
        params.add(news);
        List newsList = getNewsDao().select(params);
        if (newsList != null && !newsList.isEmpty()) {
            return (News) newsList.get(0);
        }
        return null;
    }

    /**
     * @return the newsDao
     */
    public INewsDao getNewsDao() {
        return newsDao;
    }

    /**
     * @param newsDao the newsDao to set
     */
    public void setNewsDao(INewsDao newsDao) {
        this.newsDao = newsDao;
    }
    
}
