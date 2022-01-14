package ru.job4j.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.entity.Author;
import ru.job4j.entity.Book;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book warAndPeace = Book.of("WarAndPeace");
            Book eugeneOnegin = Book.of("Eugene Onegin");
            Book twelveChairs = Book.of("12 Chairs");

            Author tolstoy = Author.of("Lev Tolstoy");
            Author pushkin = Author.of("Alexander Pushkin");
            Author petrov = Author.of("Eugene Petrov");
            Author ilf = Author.of("Ilya Ilf");

            warAndPeace.addAuthor(tolstoy);
            eugeneOnegin.addAuthor(pushkin);
            twelveChairs.addAuthor(petrov);
            twelveChairs.addAuthor(ilf);

            session.persist(warAndPeace);
            session.persist(eugeneOnegin);
            session.persist(twelveChairs);

            Book book = session.get(Book.class, twelveChairs.getId());
            session.remove(book);

            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
