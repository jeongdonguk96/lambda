package io.spring.lambda.chapter08;

public class Main {

    public static void main(String[] args) {
        Person person = new Person();
        Car car = new Car();
        Insurance insurance = new Insurance();
        insurance.setName("AIA");
        car.setInsurance(insurance);
        person.setCar(car);

        String carInsuranceName = getCarInsuranceName(person);

    }

    public static String getCarInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }
}
