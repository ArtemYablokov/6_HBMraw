package com.yabloko.app;

import com.yabloko.models.Car;
import com.yabloko.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AppRaw {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        //
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/fix_hbm_raw");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "apple");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        configuration.addResource("User.hbm.xml");

        configuration.addAnnotatedClass(Car.class);

        configuration.setProperty("hibernate.show_sql", "true");

        int n = 0;

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(new User("a", "a", 99));

        User user = session.createQuery("from User user where user.id = 15", User.class ).getSingleResult();

        Car car = new Car(0, "model", user);

        session.save(car);


        List<Car> cars = session.createQuery("from Car car", Car.class).getResultList();
        List<User> users = session.createQuery("from User car", User.class).getResultList();


        session.getTransaction().commit();
        session.close();
        sessionFactory.close();


    }
}
