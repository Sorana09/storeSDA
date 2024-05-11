package org.example;

import org.example.model.Product;
import org.example.provider.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SessionFactory sessionFactory= SessionFactoryProvider.provideSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction t=session.beginTransaction();

        Product p = new Product();
        p.setName("Mere");
        p.setPrice(12.5f);
        p.setStock(50);
        session.merge(p);
        session.persist(p);
        t.commit();

        sessionFactory.close();

    }
}
