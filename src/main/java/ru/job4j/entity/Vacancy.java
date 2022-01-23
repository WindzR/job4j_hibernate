package ru.job4j.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String requirements;

    private int salary;

    public Vacancy() {
    }

    public Vacancy(String name, String requirements, int salary) {
        this.name = name;
        this.requirements = requirements;
        this.salary = salary;
    }

    public static Vacancy of(String name, String requirements, int salary) {
        Vacancy vacancy = new Vacancy();
        vacancy.name = name;
        vacancy.requirements = requirements;
        vacancy.salary = salary;
        return vacancy;
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

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id && salary == vacancy.salary
                && Objects.equals(name, vacancy.name)
                && Objects.equals(requirements, vacancy.requirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, requirements, salary);
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", requirements='" + requirements + '\''
                + ", salary=" + salary
                + '}';
    }
}
