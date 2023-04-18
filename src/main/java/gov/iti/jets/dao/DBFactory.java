package gov.iti.jets.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBFactory {

    private volatile static DBFactory dbFactoryInstance;
    private EntityManagerFactory entityManagerFactory;

    public static DBFactory getDbFactoryInstance() {

        if(dbFactoryInstance==null) {
            synchronized (DBFactory.class) {
                if(dbFactoryInstance==null) {
                    dbFactoryInstance = new DBFactory();
                }
            }
        }
        return dbFactoryInstance;
    }

    private DBFactory() {

        this.entityManagerFactory = Persistence.createEntityManagerFactory("Sakila");
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void closeEntityManager(EntityManager entityManager) {
        entityManager.close();
    }

    public void commitTransaction(EntityManager entityManager,boolean result) {
        if(result) {
            entityManager.getTransaction().commit();
        } else {
            entityManager.getTransaction().rollback();
        }
    }

//    public void rollBackTransaction(EntityManager entityManager) {
//        entityManager.getTransaction().rollback();
//    }

}
