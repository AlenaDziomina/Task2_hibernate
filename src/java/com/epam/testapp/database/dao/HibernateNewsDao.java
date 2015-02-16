package com.epam.testapp.database.dao;

import com.epam.testapp.database.exception.DaoException;
import com.epam.testapp.model.News;
import com.epam.testapp.util.hibernate.HibernateSession;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class HibernateNewsDao implements INewsDao {
    private static final Logger LOGGER = Logger.getLogger(HibernateNewsDao.class);
    private HibernateSession hibernateSession;
    private static final String NEWS_ID = "id";
    private static final String NEWS_DATE = "date";
    public static final String DELETE_NEWS_QUERY = "Delete News where id in (:id)";


    public HibernateNewsDao() {}

    public HibernateSession getHibernateSession() {
            return hibernateSession;
    }

    public void setHibernateSession(HibernateSession hibernateSession) {
            this.hibernateSession = hibernateSession;
    }

    @Override
    public Integer insert(News news) throws DaoException {
        Session session = hibernateSession.getSession();
        Transaction transaction;
        Integer id = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(news);
            transaction.commit();
        } catch (HibernateException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            session.close();
        }
        return id;
    }

    @Override
    public void update(News news) throws DaoException {
        Session session = hibernateSession.getSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            session.update(news);
            transaction.commit();
        } catch (HibernateException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            session.close();
        }
    }

    

    @Override
    public void delete(List<News> newsList) throws DaoException {
        Session session = hibernateSession.getSession();
        Transaction transaction;
        try {
            
            Object[] ids = new Object[newsList.size()];
            int i = 0;
            for (News news : newsList) {
                ids[i++] = news.getId();
            }
            transaction = session.beginTransaction();
            Query query = session.createQuery(DELETE_NEWS_QUERY);
            query.setParameterList(NEWS_ID, ids);
            query.executeUpdate();
//            Criteria criteria = session.createCriteria(News.class);
//            newsList.stream().forEach((news) -> {
//                criteria.add(Restrictions.eq(NEWS_ID, news.getId()));
//            });
//            List<News> list = (List<News>) criteria.list();
//            list.stream().forEach((news) -> {
//                session.delete(news);
//            });
            transaction.commit();
        } catch (HibernateException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            session.close();
        }
    }

    @Override
    public List selectAll() throws DaoException {
        Session session = hibernateSession.getSession();
        Transaction transaction;
        List<News> newsList = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(News.class);
            criteria.addOrder(Order.desc(NEWS_DATE));
            newsList = (List<News>) criteria.list();
            transaction.commit();
        } catch (HibernateException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            session.close();
        }
        return newsList;
    }
 
    @Override
    public News fetchById(News news) throws DaoException {
        Session session = hibernateSession.getSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            news = (News) session.get(News.class, news.getId());
            transaction.commit();
        } catch (HibernateException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            session.close();
        }
        return news;
    }
}

