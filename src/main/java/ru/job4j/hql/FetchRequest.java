package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.entity.Candidate;
import ru.job4j.entity.DatabaseVacancies;
import ru.job4j.entity.Vacancy;

public class FetchRequest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    public static void main(String[] args) {
        FetchRequest fetchRequest = new FetchRequest();

        fetchRequest.addData();

        Candidate michael = fetchRequest.fetchRequest("Michael");
        System.out.println(michael);
    }

    private void addData() {
        try {
            Session session = sf.openSession();
            session.beginTransaction();

            Vacancy vacancyOne =
                    Vacancy.of("Java Junior", "To know methods Equals & Hashcode", 500);
            Vacancy vacancyTwo =
                    Vacancy.of("Java Middle", "Multithreading, Spring, Hibernate", 2500);
            Vacancy vacancyThree =
                    Vacancy.of("Java Senior", "No criminal record", 4500);
            session.save(vacancyOne);
            session.save(vacancyTwo);
            session.save(vacancyThree);

            DatabaseVacancies databaseVacancy = DatabaseVacancies.of("Software Developers");
            databaseVacancy.addVacancy(vacancyOne);
            databaseVacancy.addVacancy(vacancyTwo);
            databaseVacancy.addVacancy(vacancyThree);
            session.save(databaseVacancy);

            Candidate javaCandidate = Candidate.of("Michael", 2, 1800);
            javaCandidate.setDatabase(databaseVacancy);
            session.save(javaCandidate);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private Candidate fetchRequest(String name) {
        Candidate candidate = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            candidate = (Candidate) session.createQuery(
                            "SELECT distinct can from Candidate can"
                                    + " JOIN FETCH can.database data "
                                    + "JOIN FETCH data.vacancies WHERE can.name = :nameParam"
                    )
                    .setParameter("nameParam", name)
                    .uniqueResult();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return candidate;
    }
}
