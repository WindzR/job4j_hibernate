package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.entity.Candidate;

import javax.persistence.Query;
import java.util.List;

public class HbmRun {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    public static void main(String[] args) {
        HbmRun hbmRun = new HbmRun();

        List<Candidate> candidates = hbmRun.findAllCandidates();
        candidates.forEach(System.out::println);

        Candidate findById = hbmRun.findCandidateById(2);
        System.out.println(findById);

        Candidate updateCandidate = hbmRun.updateCandidateById("Oleg", 3, 1800, 2);
        System.out.println(updateCandidate);

        hbmRun.deleteCandidate(2);

        Candidate findByName = hbmRun.findCandidateByName("Polina");
        System.out.println(findByName);
    }

    private List<Candidate> findAllCandidates() {
        List<Candidate> candidates = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            candidates = session.createQuery("from Candidate")
                    .getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return candidates;
    }

    private Candidate findCandidateById(int id) {
        Candidate candidate = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            candidate = (Candidate) session.createQuery(
                    "from Candidate can WHERE can.id = :idParam"
                    )
                            .setParameter("idParam", id)
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

    private Candidate findCandidateByName(String name) {
        Candidate candidate = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            candidate = (Candidate) session.createQuery(
                            "from Candidate can WHERE can.name = :nameParam"
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

    private Candidate updateCandidateById(String name, int expirience, int salary, int id) {
        Candidate candidate = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();

            final Query query = session.createQuery(
                            "update Candidate can SET can.name = :nameParam,"
                                    + " can.expirience = :expirienceParam, "
                                    + "can.salary =:salaryParam WHERE id = :idParam"
                    )
                    .setParameter("nameParam", name)
                    .setParameter("expirienceParam", expirience)
                    .setParameter("salaryParam", salary)
                    .setParameter("idParam", id);
            query.executeUpdate();
            candidate = session.get(Candidate.class, id);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return candidate;
    }

    private void deleteCandidate(int id) {
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            final Query query = session.createQuery(
                    "DELETE from Candidate can WHERE can.id = :canId")
                    .setParameter("canId", id);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
