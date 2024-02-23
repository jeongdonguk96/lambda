package io.spring.lambda.chapter03;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("potato", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<String> helloWorld = Arrays.asList("Hello", "World");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<String> myMenu1 = menu.stream()
                // 조건에 해당하는 모든 요소를 반환한다.
                .filter(dish -> dish.getCalories() > 400)
                .map(Dish::getName)
                .limit(5)
                .toList();

        List<String> myMenu2 = menu.stream()
                // 조건에 해당하지 않는 요소를 만나면 스트림과 반환을 멈춘다.
                .takeWhile(dish -> dish.getCalories() > 400)
                .map(Dish::getName)
                .limit(5)
                .toList();

        List<String> myMenu3 = menu.stream()
                // 조건에 해당하지 않는 요소를 만나면 스트림과 반환을 시작한다.
                .dropWhile(dish -> dish.getCalories() > 400)
                .map(Dish::getName)
                .limit(5)
                .toList();

        List<Dish> myMenu4 = menu.stream()
                .filter(dish -> Dish.Type.MEAT.equals(dish.getType()))
                .limit(2)
                .toList();

        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .toList();

        List<Integer> menuWordLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .toList();

        List<String> list = helloWorld.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .toList();

        List<Integer> list1 = numbers.stream()
                .map(n -> n * n)
                .toList();

        System.out.println("myMenu1 = " + myMenu1);
        System.out.println("myMenu2 = " + myMenu2);
        System.out.println("myMenu3 = " + myMenu3);
        System.out.println("myMenu4 = " + myMenu4);
        System.out.println("wordLengths = " + wordLengths);
        System.out.println("menuWordLengths = " + menuWordLengths);
        System.out.println("list = " + list);
        System.out.println("list1 = " + list1);
    }

}