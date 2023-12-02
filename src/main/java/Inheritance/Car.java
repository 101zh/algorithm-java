package Inheritance;

public class Car extends Vehicle{
    String model ="AA4";

    public static void main(String[] args) {
        Car car = new Car();
        car.drive();
        System.out.println(car.brand+" "+car.model);
    }    
}
