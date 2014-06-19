package com.soulmonk.ndfsm.service.angular;

import java.util.List;

public interface CarService {
    public List<String> getAllCars();

    public void addCar(String car);

    public void deleteCar(String car);

    public void deleteAll();
}
