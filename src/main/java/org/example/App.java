package org.example;

import org.example.model.Product;
import org.example.provider.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("0. Iesire");
            System.out.println("1. Vizualizare produse");
            System.out.println("2. Adaugare produs");
            System.out.println("3. Stergere produs");
            System.out.println("4. Editare produs");
            int number = scanner.nextInt();
            if (number == 0)
                break;
            else
                switch (number) {
                    case 1: {
                        System.out.println(viewProducts());
                        break;
                    }
                    default:
                        System.out.println("Nu exista optiunea. Va rugam reincercati.");
                        break;
                }
        }

    }

    private static void addProduct() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        Product p = new Product();
        p.setName("Mere");
        p.setPrice(12.5f);
        p.setStock(50);
        session.merge(p);
        session.persist(p);
        t.commit();

        sessionFactory.close();
    }

   private static List<Product> viewProducts(){
       SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
       Session session = sessionFactory.openSession();
//       Query<Product> query = session.createNativeQuery("SELECT * FROM product",Product.class);
       Query<Product> query = session.createQuery("SELECT p FROM Product p",Product.class);
       return query.getResultList();
   }

}
