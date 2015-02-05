package com.epam.testapp.database.dao;

import com.epam.testapp.database.exception.DaoException;
import com.epam.testapp.model.News;
import com.epam.testapp.util.hibernate.HibernateSession;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class HibernateNewsDao implements INewsDao {
    private static final Logger LOGGER = Logger.getLogger(HibernateNewsDao.class);
    private HibernateSession hibernateSession;
    private static final String NEWS_ID = "id";
    private static final String NEWS_DATE = "date";

    public HibernateNewsDao() {}

    public HibernateSession getHibernateSession() {
            return hibernateSession;
    }

    public void setHibernateSession(HibernateSession hibernateSession) {
            this.hibernateSession = hibernateSession;
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
        } finally {
            session.close();
        }
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
        } finally {
            session.close();
        }
        return id;
    }

    @Override
    public void delete(List<Integer> idList) throws DaoException {
        Session session = hibernateSession.getSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(News.class);
            idList.stream().forEach((id) -> {
                criteria.add(Restrictions.eq(NEWS_ID, id));
            });
            List<News> list = (List<News>) criteria.list();
            list.stream().forEach((news) -> {
                session.delete(news);
            });
            transaction.commit();
        } catch (HibernateException exc) {
            LOGGER.error(exc);
        } finally {
            session.close();
        }
    }

    @Override
    public List select(List<News> newsList) throws DaoException {
        Session session = hibernateSession.getSession();
        Transaction transaction;
        List<News> list = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(News.class);
            criteria.addOrder(Order.desc(NEWS_DATE));
            if (newsList != null) {
                newsList.stream().filter((news) -> (news.getId() != null)).forEach((news) -> {
                    criteria.add(Restrictions.eq(NEWS_ID, news.getId()));
                });
            }
            list = (List<News>) criteria.list();
            transaction.commit();
        } catch (HibernateException exc) {
            LOGGER.error(exc);
            throw new DaoException(exc);
        } finally {
            session.close();
        }
        return list;
    }
}

