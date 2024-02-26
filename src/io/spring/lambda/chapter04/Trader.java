package io.spring.lambda.chapter04;

public class Trader {
    private final String name;
    private final String city;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
