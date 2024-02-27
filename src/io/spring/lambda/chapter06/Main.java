package io.spring.lambda.chapter06;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 내부 데이터를 변경하는 것은 가능하지만, 배열의 길이는 변경할 수 없다.
        List<String> list1 = Arrays.asList("minsu", "yongju", "hira");
        list1.set(0, "bebe");
        System.out.println("list1 = " + list1);

        // 배열의 길이가 정해져있기 때문에 이 길이를 변경하는 것을 불가능하다.
        List<String> list2 = Arrays.asList("minsu", "yongju", "hira");
        list2.add("bebe");
        System.out.println("list2 = " + list2);

        // List.of()는 불변 컬렉션을 만들기 때문에 변경이 아예 불가능하다.
        List<String> list3 = List.of("Kiana", "polina");
        list3.set(0, "kyle");
        System.out.println("list3 = " + list3);
    }

}
