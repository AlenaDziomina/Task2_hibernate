package com.epam.testapp.util.hibernate;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSession {
    private static final Logger LOGGER = Logger.getLogger(HibernateSession.class);
    private String hibernateCfgPath;
    private SessionFactory sessionFactory;
    
    private HibernateSession(){}
    
    public void init() throws ExceptionInInitializerError{
        try {
            ServiceRegistry serviceRegistry;
            Configuration configuration = new Configuration().configure(hibernateCfgPath);
            Properties properties = configuration.getProperties();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            LOGGER.error(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
            return sessionFactory;
    }

    public Session getSession() {
            Session session = sessionFactory.openSession();
            return session;
    }

    /**
     * @return the hibernateCfgPath
     */
    public String getHibernateCfgPath() {
        return hibernateCfgPath;
    }

    /**
     * @param hibernateCfgPath the hibernateCfgPath to set
     */
    public void setHibernateCfgPath(String hibernateCfgPath) {
        this.hibernateCfgPath = hibernateCfgPath;
    }
}
