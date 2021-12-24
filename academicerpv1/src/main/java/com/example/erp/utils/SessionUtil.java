package com.example.erp.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SessionUtil {

    public static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            SESSION_FACTORY = configuration.buildSessionFactory();

        }catch (Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException{
        return SESSION_FACTORY.openSession();
    }

}


