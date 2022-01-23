package ru.job4j.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "data_of_vacancies")
public class DatabaseVacancies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_id")
    private Set<Vacancy> vacancies = new HashSet<>();

    public DatabaseVacancies() {
    }

    public DatabaseVacancies(String name) {
        this.name = name;
    }

    public static DatabaseVacancies of(String name) {
        DatabaseVacancies databaseVacancies = new DatabaseVacancies();
        databaseVacancies.name = name;
        return databaseVacancies;
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
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

    public Set<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Set<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DatabaseVacancies that = (DatabaseVacancies) o;
        return id == that.id && Objects.equals(name, that.name)
                && Objects.equals(vacancies, that.vacancies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vacancies);
    }

    @Override
    public String toString() {
        return "DatabaseVacancies{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", vacancies=" + vacancies
                + '}';
    }
}
