package io.spring.lambda.chapter02;

import java.util.ArrayList;
import java.util.List;

public class LambdaBefore1 {

    public static void main(String[] args) {
        Apple apple1 = new Apple();
        apple1.setColor(Color.GREEN);
        apple1.setWeight(190);

        Apple apple2 = new Apple();
        apple2.setColor(Color.RED);
        apple2.setWeight(140);

        List<Apple> inventory = new ArrayList<>();
        inventory.add(apple1);
        inventory.add(apple2);

        List<Apple> redApples = filterRedApples(inventory);
        List<Apple> greenApples = filterGreenApples(inventory);
        List<Apple> greenApples2 = filterApplesByColors(inventory, Color.GREEN);
        List<Apple> heavyApples = filterApplesByWeight(inventory, 150);

    }

    // 1단계
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> appleList = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN.equals(apple.getColor())) {
                appleList.add(apple);
            }
        }

        return appleList;
    }

    // 1단계
    public static List<Apple> filterRedApples(List<Apple> inventory) {
        List<Apple> appleList = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.RED.equals(apple.getColor())) {
                appleList.add(apple);
            }
        }

        return appleList;
    }

    // 2단계
    public static List<Apple> filterApplesByColors(List<Apple> inventory, Color color) {
        List<Apple> appleList = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                appleList.add(apple);
            }
        }

        return appleList;
    }

    // 2단계
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> appleList = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                appleList.add(apple);
            }
        }

        return appleList;
    }


}
