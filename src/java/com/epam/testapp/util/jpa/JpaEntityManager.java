package com.epam.testapp.util.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaEntityManager {
    public EntityManagerFactory getEntityManagerFactory() {
        EntityManagerFactory factory = Persistence
                        .createEntityManagerFactory("newsJpaUnit");
        return factory;
    }
}
