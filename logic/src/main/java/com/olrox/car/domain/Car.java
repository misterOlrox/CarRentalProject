package com.olrox.car.domain;

import com.olrox.order.domain.CarOrder;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Car.getAll", query = "SELECT c from Car c")
public class Car {
    @Id
    private String carNumber;

    @ManyToOne
    private CarModel carModel;

    private int fuel;

    @OneToOne
    private Coordinates coordinates;

    @OneToMany(mappedBy = "car")
    private List<CarOrder> orders;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<CarOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CarOrder> orders) {
        this.orders = orders;
    }
}
