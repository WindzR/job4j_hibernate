package ru.job4j.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.entity.CarBrand;
import ru.job4j.entity.CarModel;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            CarBrand lada = CarBrand.of("Lada");
            CarModel first = CarModel.of("Vesta");
            CarModel second = CarModel.of("Vesta Sport");
            CarModel third = CarModel.of("Granta");
            CarModel forth = CarModel.of("Kalina");
            CarModel fifth = CarModel.of("Largus");
            session.save(first);
            session.save(second);
            session.save(third);
            session.save(forth);
            session.save(fifth);
            lada.addCarModel(session.load(CarModel.class, first.getId()));
            lada.addCarModel(session.load(CarModel.class, second.getId()));
            lada.addCarModel(session.load(CarModel.class, third.getId()));
            lada.addCarModel(session.load(CarModel.class, forth.getId()));
            lada.addCarModel(session.load(CarModel.class, fifth.getId()));

            session.save(lada);

            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
