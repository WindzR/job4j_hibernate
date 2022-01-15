package ru.job4j.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.entity.CarBrand;
import ru.job4j.entity.CarModel;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {

    public static void main(String[] args) {
        List<CarBrand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            list = session.createQuery(
                    "select distinct carbrand from CarBrand carbrand join fetch carbrand.models"
            ).getResultList();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (CarModel carModel : list.get(0).getModels()) {
            System.out.println(carModel);
        }
    }
}
