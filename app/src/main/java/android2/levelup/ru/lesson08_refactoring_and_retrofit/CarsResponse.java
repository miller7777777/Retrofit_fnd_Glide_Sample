package android2.levelup.ru.lesson08_refactoring_and_retrofit;


import java.util.List;

public class CarsResponse {
    private List<Car> cars;

    public CarsResponse(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }
}
