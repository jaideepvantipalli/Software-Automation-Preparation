
import java.util.*;
import java.util.stream.*;

public class OptionalClassDemo {

    public static void main(String[] args) {

        System.out.println("===== 1. CREATING OPTIONAL OBJECTS =====");

        // of() - Creates Optional with non-null value
        Optional<String> name = Optional.of("Jaideep");
        System.out.println("of(): " + name);

        // ofNullable() - Accepts null
        Optional<String> nullable = Optional.ofNullable(null);
        System.out.println("ofNullable(): " + nullable);

        // empty() - Creates empty Optional
        Optional<String> empty = Optional.empty();
        System.out.println("empty(): " + empty);

        // isPresent()
        System.out.println("isPresent(): " + name.isPresent());

        // isEmpty() - Java 11+
        System.out.println("isEmpty(): " + empty.isEmpty());

        // get()
        System.out.println("get(): " + name.get());

        // Never call get() blindly on an empty Optional.


        System.out.println("\n===== 3. DEFAULT VALUE METHODS =====");

        // orElse()
        String value1 = nullable.orElse("Default Name");
        System.out.println("orElse(): " + value1);

        // orElseGet()
        String value2 = nullable.orElseGet(
                () -> "Generated Default"
        );
        System.out.println("orElseGet(): " + value2);

        // orElseThrow()
        try {
            String value3 = empty.orElseThrow(
                    () -> new NoSuchElementException("Value not found")
            );
            System.out.println(value3);
        } catch (NoSuchElementException e) {
            System.out.println("orElseThrow(): " + e.getMessage());
        }


        System.out.println("\n===== 4. CONDITIONAL METHODS =====");

        // ifPresent()
        name.ifPresent(
                n -> System.out.println("ifPresent(): " + n)
        );

        // ifPresentOrElse() - Java 9+
        empty.ifPresentOrElse(
                n -> System.out.println("Value: " + n),
                () -> System.out.println("ifPresentOrElse(): No value")
        );


        System.out.println("\n===== 5. TRANSFORMATION METHODS =====");

        // map()
        Optional<Integer> length = name.map(String::length);
        System.out.println("map(): " + length);

        // flatMap()
        Optional<String> upper = name.flatMap(
                n -> Optional.of(n.toUpperCase())
        );
        System.out.println("flatMap(): " + upper);

        // filter()
        Optional<String> filtered = name.filter(
                n -> n.length() > 5
        );
        System.out.println("filter(): " + filtered);

        Optional<String> rejected = name.filter(
                n -> n.length() > 20
        );
        System.out.println("Rejected filter(): " + rejected);


        System.out.println("\n===== 6. ALTERNATIVE OPTIONAL =====");

        // or() - Java 9+
        Optional<String> alternative = empty.or(
                () -> Optional.of("Alternative Value")
        );
        System.out.println("or(): " + alternative);


        System.out.println("\n===== 7. OPTIONAL TO STREAM =====");

        // stream() - Java 9+
        List<String> result = name.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println("stream(): " + result);

        // Empty Optional produces an empty Stream
        long count = empty.stream().count();
        System.out.println("Empty stream count(): " + count);


        System.out.println("\n===== 8. OPTIONAL WITH STREAM API =====");

        List<Integer> numbers = List.of(10, 20, 30, 40, 50);

        Optional<Integer> first = numbers.stream()
                .filter(n -> n > 25)
                .findFirst();

        System.out.println(
                "findFirst(): " + first.orElse(-1)
        );

        Optional<Integer> maximum = numbers.stream()
                .max(Comparator.naturalOrder());

        System.out.println(
                "max(): " + maximum.orElse(0)
        );


        System.out.println("\n===== 9. OPTIONAL INT =====");

        OptionalInt intValue = OptionalInt.of(100);

        System.out.println("getAsInt(): " + intValue.getAsInt());
        System.out.println("isPresent(): " + intValue.isPresent());
        System.out.println("orElse(): " + intValue.orElse(0));

        OptionalInt emptyInt = OptionalInt.empty();

        System.out.println("Empty int: " + emptyInt.orElse(-1));


        System.out.println("\n===== 10. OPTIONAL LONG =====");

        OptionalLong longValue = OptionalLong.of(100000L);

        System.out.println("getAsLong(): " + longValue.getAsLong());
        System.out.println("isPresent(): " + longValue.isPresent());
        System.out.println("orElse(): " + longValue.orElse(0L));


        System.out.println("\n===== 11. OPTIONAL DOUBLE =====");

        OptionalDouble doubleValue = OptionalDouble.of(99.99);

        System.out.println(
                "getAsDouble(): " + doubleValue.getAsDouble()
        );

        System.out.println(
                "isPresent(): " + doubleValue.isPresent()
        );

        System.out.println(
                "orElse(): " + doubleValue.orElse(0.0)
        );


        System.out.println("\n===== OPTIONAL DEMO COMPLETED =====");
    }
}
