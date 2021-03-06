package com.olrox.map;

import com.olrox.domain.car.Car;
import com.olrox.domain.car.CarStatus;
import com.olrox.ejb.CarsManager;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class MapView implements Serializable {
    private MapModel simpleModel;

    @EJB
    private CarsManager carsManager;

    @PostConstruct
    public void init() {
        simpleModel = new CustomMapModel();
        List<Car> cars = carsManager.getAll();
        for(Car car: cars){
            if(car.getCarStatus() == CarStatus.FREE) {
                LatLng coord = new LatLng(car.getCoordinates().getLatitude(),
                        car.getCoordinates().getLongitude());
                Marker marker = new Marker(coord, car.getCarNumber(), car.getId());
                simpleModel.addOverlay(marker);
            }
        }
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
}
