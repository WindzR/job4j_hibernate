package ru.job4j.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int expirience;

    private int salary;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "database_id")
    private DatabaseVacancies database;

    public Candidate() {
    }

    public Candidate(String name, int expirience, int salary) {
        this.name = name;
        this.expirience = expirience;
        this.salary = salary;
    }

    public static Candidate of(String name, int expirience, int salary) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.expirience = expirience;
        candidate.salary = salary;
        return candidate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpirience() {
        return expirience;
    }

    public void setExpirience(int expirience) {
        this.expirience = expirience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public DatabaseVacancies getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseVacancies database) {
        this.database = database;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id && expirience == candidate.expirience
                && salary == candidate.salary && Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", expirience=" + expirience
                + ", salary=" + salary
                + ", database=" + database
                + '}';
    }
}
