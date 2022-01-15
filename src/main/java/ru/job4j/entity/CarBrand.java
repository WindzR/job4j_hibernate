package ru.job4j.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "carBrand",
               cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> models = new ArrayList<>();

    public CarBrand() {
    }

    public CarBrand(String name) {
        this.name = name;
    }

    public static CarBrand of(String name) {
        CarBrand carBrand = new CarBrand();
        carBrand.name = name;
        return carBrand;
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

    public List<CarModel> getModels() {
        return models;
    }

    public void setModels(List<CarModel> models) {
        this.models = models;
    }

    public void addCarModel(CarModel model) {
        this.models.add(model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarBrand carBrand = (CarBrand) o;
        return id == carBrand.id && Objects.equals(name, carBrand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CarBrand{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
