package io.spring.lambda.chapter04;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Trader minsu = new Trader("minsu", "seoul");
        Trader enzo = new Trader("enzo", "madrid");
        Trader pedro = new Trader("pedro", "madrid");
        Trader joy = new Trader("joy", "madrid");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(joy, 2011, 300),
                new Transaction(minsu, 2012, 1000),
                new Transaction(minsu, 2011, 400),
                new Transaction(enzo, 2012, 710),
                new Transaction(enzo, 2012, 700),
                new Transaction(pedro, 2012, 950)
        );

        // 1. 2011년에 발생한 모든 트랜잭션을 찾아 오름차순으로 정렬하시오.
        List<Transaction> list1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .toList();
        System.out.println("list1 = " + list1);

        // 2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
        List<String> list2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .toList();
        System.out.println("list2 = " + list2);

        // 3. 마드리드에서 근무하는 모든 거래자를 찾아 이름 순으로 정렬하시오.
        List<Trader> list3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "madrid".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
        System.out.println("list3 = " + list3);

        // 4. 모든 거래자의 이름을 알파벳 순으로 정렬하시오.
        List<String> list4 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted(Comparator.naturalOrder())
                .toList();
        System.out.println("list4 = " + list4);

        // 5. 서울에 거래자가 있는가?
        boolean present = transactions.stream()
                .anyMatch(transaction -> "seoul".equals(transaction.getTrader().getCity()));
        System.out.println("present = " + present);

        // 6. 마드리드에 거주하는 거래자의 모든 트랜잭션 값을 출력하시오.
        transactions.stream()
                .filter(transaction -> "madrid".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 7. 전체 트랜잭션 중 최소값은 얼마인가?
        Optional<Integer> reduce1 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        System.out.println("reduce1 = " + reduce1.orElse(0));

        // 8. 전체 트랜잭션 중 최대값은 얼마인가?
        Optional<Integer> reduce2 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println("reduce2 = " + reduce2.orElse(0));

        // 전체 트랜잭션 중 최소값은 얼마인가?
        OptionalInt min = transactions.stream()
                .mapToInt(Transaction::getValue)
                .min();
        System.out.println("min = " + min.orElse(0));

        // 전체 트랜잭션 중 최대값은 얼마인가?
        OptionalInt max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println("max = " + max.orElse(0));

    }
}
