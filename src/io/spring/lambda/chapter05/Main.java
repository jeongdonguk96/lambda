package io.spring.lambda.chapter05;

import io.spring.lambda.chapter03.Dish;

import java.util.*;
import java.util.stream.Collectors;

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

        // 메뉴 전체 칼로리 총합
        int totalCalories = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        int totalCalories2 = menu.stream()
                .mapToInt(Dish::getCalories).sum();
        // 메뉴명 모두 합치기
        String menuNames = menu.stream()
                .map(Dish::getName).collect(Collectors.joining());
        // 메뉴명 반점으로 구분해서 모두 합치기
        String menuNamesWithDelimeter = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
        // 메뉴를 타입으로 분류하기
        Map<Dish.Type, List<Dish>> menuByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        // 메뉴의 타입별 개수 구하기
        Map<Dish.Type, Long> typeCount = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.counting()));
        // 메뉴를 타입별로 나누고 칼로리가 가장 많은 메뉴 구하기
        Map<Dish.Type, Optional<Dish>> mostHighCalorieDishesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        // 메뉴를 타입별로 나누고 칼로리를 더한다.
        Map<Dish.Type, Integer> caloriesSumByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.summingInt(Dish::getCalories)));
        // 메뉴를 타입별로 분류하면서, 400kcal 이하, 700kcal 이하, 1000kcal 이하 세 분류로 나눈다.
        Map<Dish.Type, Map<String, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.groupingBy(dish -> {
                                    if (dish.getCalories() <= 400)
                                        return "DIET";
                                    else if (dish.getCalories() <= 700)
                                        return "NORMAL";
                                    else if (dish.getCalories() <= 1000)
                                        return "FAT";
                                    else
                                        return null;
                                })
                        )
                );
        // 메뉴에서 채식 메뉴와 아닌 것을 나눈다.
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));

        // 메뉴에서 채식 메뉴와 아닌 것을 나눈다.
        Map<Boolean, Map<Object, List<Dish>>> collect = menu.stream()
                .collect(Collectors.groupingBy(Dish::isVegetarian,
                        Collectors.groupingBy(dish -> {
                            if (dish.isVegetarian())
                                return "VEGE";
                            else
                                return "NOVEGE";
                        }))
                );

        Map<Boolean, Map<Boolean, List<Dish>>> vegeOrNonvegeHigherThan500kcal = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.partitioningBy(dish -> dish.getCalories() > 500)));

        Map<Boolean, Long> vegeOrNonvegeCount = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.counting()));


        System.out.println("totalCalories = " + totalCalories);
        System.out.println("totalCalories2 = " + totalCalories2);
        System.out.println("menuNames = " + menuNames);
        System.out.println("menuNamesWithDelimeter = " + menuNamesWithDelimeter);
        System.out.println("menuByType = " + menuByType);
        System.out.println("typeCount = " + typeCount);
        System.out.println("mostHighCalorieDishesByType = " + mostHighCalorieDishesByType);
        System.out.println("caloriesSumByType = " + caloriesSumByType);
        System.out.println("dishesByTypeCaloricLevel = " + dishesByTypeCaloricLevel);
        System.out.println("partitionedMenu = " + partitionedMenu);
        System.out.println("collect = " + collect);
        System.out.println("vegeOrNonvegeHigherThan500kcal = " + vegeOrNonvegeHigherThan500kcal);
        System.out.println("vegeOrNonvegeCount = " + vegeOrNonvegeCount);

    }
}
