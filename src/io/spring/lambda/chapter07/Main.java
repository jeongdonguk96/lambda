package io.spring.lambda.chapter07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, Object> map = Map.ofEntries(
                Map.entry("minsu", "Star Wars"),
                Map.entry("yongju", "Matrix"),
                Map.entry("hira", "007"));

        // null을 반환한다.
        System.out.println(map.get("bora"));
        // Map에서 null 값을 꺼낼 시 null 대신 원하는 값을 반환한다.
        System.out.println(map.getOrDefault("minsu", "none"));
        System.out.println(map.getOrDefault("bora", "none"));


        Map<String, Integer> movies = new HashMap<>();
        movies.put("007", 20);
        movies.put("Matrix", 15);
        movies.put("Top Gun", 5);

        // 컬렉션 API 사용 X
        Iterator<Map.Entry<String, Integer>> iterator = movies.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 10) {
                iterator.remove();
            }
        }
        System.out.println("movies = " + movies);

        // 컬렉션 API 사용 O
        // entrySet(): 맵의 키-값 쌍을 나타내는 Set<Map.Entry<String, Integer>>을 반환한다.
        movies.entrySet().removeIf(entry -> entry.getValue() < 10);
        System.out.println("movies = " + movies);

    }
}
