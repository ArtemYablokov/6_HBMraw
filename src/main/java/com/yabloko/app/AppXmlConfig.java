package com.yabloko.app;

import java.util.List;
import java.util.Iterator;

import com.yabloko.models.Car;
import com.yabloko.models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class AppXmlConfig {
    private static SessionFactory factory;
    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().
                            setProperty("hibernate.hbm2ddl.auto", "update").
                    addAnnotatedClass(Car.class).
                    addResource("User.hbm.xml").
                    buildSessionFactory();
            // здесь уже будет создано пробное соединение с БД по УРЛу
            // + создание/корректирование таблиц на основе XML / аннотаций

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        AppXmlConfig ME = new AppXmlConfig();


        Integer empID1 = ME.addUser("Zara", "Ali", 1000);
        Integer empID2 = ME.addUser("Daisy", "Das", 5000);
        Integer empID3 = ME.addUser("John", "Paul", 10000);

        ME.listUsers();
        ME.updateUser(empID1, 5000);
        ME.deleteUser(empID3);
        ME.listUsers();

        factory.close();
    }

    public Integer addUser(String fname, String lname, int salary){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer UserID = null;

        try {
            tx = session.beginTransaction();
            User User = new User();
            User.setFirstName(fname);
            User.setLastName(lname);
            User.setSalary(salary);
            UserID = (Integer) session.save(User);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return UserID;
    }

    public void listUsers( ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // HQL - обращение к КЛАСАМ
            List Users = session.createQuery("FROM User").list();

            for (Iterator iterator = Users.iterator(); iterator.hasNext();){
                User User = (User) iterator.next();
                System.out.print("First Name: " + User.getFirstName());
                System.out.print("  Last Name: " + User.getLastName());
                System.out.print("  Salary: " + User.getSalary());
                System.out.println();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateUser(Integer UserID, Integer salary ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User User = (User)session.get(User.class, UserID);
            User.setSalary( salary );
            session.update(User);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteUser(Integer UserID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User User = (User)session.get(User.class, UserID);
            session.delete(User);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}