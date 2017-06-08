package android2.levelup.ru.lesson08_refactoring_and_retrofit;


public class Car {
    private String name;
    private String image;
    private String vin;

    public Car(String name, String image, String vin) {
        this.name = name;
        this.image = image;
        this.vin = vin;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getVin() {
        return vin;
    }
}
