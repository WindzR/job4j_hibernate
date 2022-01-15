package ru.job4j.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carbrand_id")
    private CarBrand carBrand;

    public CarModel() {
    }

    public CarModel(String name) {
        this.name = name;
    }

    public static CarModel of(String name) {
        CarModel carModel = new CarModel();
        carModel.name = name;
        return carModel;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarModel carModel = (CarModel) o;
        return id == carModel.id && Objects.equals(name, carModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CarModel{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", carBrand=" + carBrand
                + '}';
    }
}
