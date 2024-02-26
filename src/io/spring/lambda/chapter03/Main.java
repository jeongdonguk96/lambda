package io.spring.lambda.chapter03;

import java.util.*;

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

//        List<String> dishName = new ArrayList<>();
//        for (Dish dish : menu) {
//            if (dish.getCalories() >= 400 && Dish.Type.MEAT != dish.getType()) {
//                dishName.add(dish.getName());
//            }
//        }
//        System.out.println("dishName = " + dishName);

        List<String> dishName = menu.stream()
                .filter(dish -> dish.getCalories() >= 400)
                .filter(dish -> dish.getType() != Dish.Type.MEAT)
                .map(Dish::getName)
                .toList();
        System.out.println("dishName = " + dishName);

        // filter() : 스트림에서 특정 조건을 기반으로 요소를 선택한다.
        // map()    : 스트림의 요소를 다른 형태로 변환한다.
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

        // 스트림에서 프리디케이트가 적어도 한 요소와 일치하는지 확인한다.
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("This menu is vegetarian friendly");
        }

        // 스트림에서 프리디케이트가 모든 요소가 일치하는지 확인한다.
        boolean isHealthy = menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);

        // 스트림에서 프리디케이트와 일치하는 요소가 없는지 확인한다.
        boolean isHealthy2 = menu.stream()
                .noneMatch(dish -> dish.getCalories() >= 1000);

        menu.stream()
                .filter(Dish::isVegetarian)
                // 스트림에서 임의의 요소를 반환한다.
                .findAny()
                // 만약 존재하면 다음 내용을 실행한다.
                .ifPresent(vegeDish -> System.out.println("vegeDish = " + vegeDish));

        Optional<Integer> first = numbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                // 스트림에서 첫 번째 요소를 반환한다.
                .findFirst();

        // 스트림의 요소를 처리(계산)해서 값으로 반환한다.
        Integer sum = numbers.stream().reduce(0, Integer::sum);
        Optional<Integer> maxNum = numbers.stream().reduce(Integer::max);
        Optional<Integer> minNum = numbers.stream().reduce(Integer::min);
        long menuCount = menu.stream().filter(Dish::isVegetarian).count();

        System.out.println("myMenu1 = " + myMenu1);
        System.out.println("myMenu2 = " + myMenu2);
        System.out.println("myMenu3 = " + myMenu3);
        System.out.println("myMenu4 = " + myMenu4);
        System.out.println("wordLengths = " + wordLengths);
        System.out.println("menuWordLengths = " + menuWordLengths);
        System.out.println("list = " + list);
        System.out.println("list1 = " + list1);
        System.out.println("isHealthy = " + isHealthy);
        System.out.println("isHealthy2 = " + isHealthy2);
        System.out.println("first = " + first);
        System.out.println("sum = " + sum);
        System.out.println("maxNum = " + maxNum);
        System.out.println("minNum = " + minNum);
        System.out.println("menuCount = " + menuCount);
    }

}