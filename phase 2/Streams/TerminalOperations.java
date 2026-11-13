import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TerminalOperations {
    public static void main(String[] args) {
        List<String> names =
                Arrays.asList("Java", "Python", "C");

        names.stream()
                .forEach(System.out::println);

        long count = names.stream()
                .count();
        System.out.println(count);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> evennumbers = numbers.stream()
                .filter(n -> n%2 == 0 )
                .collect(Collectors.toList());

        System.out.println(evennumbers);

        int sum=numbers.stream()
                .reduce(0,Integer::sum);
        System.out.println(sum);

        Optional<Integer> min = numbers.stream()
                .min(Comparator.comparing(Integer::intValue));
        System.out.println(min.get());
        Optional<Integer> max = numbers.stream()
                .max(Comparator.comparing(Integer::intValue));
        System.out.println(max.get());

        Optional<Integer> optional = numbers.stream().findFirst();
        System.out.println(optional.get());
        Optional<Integer> optional1 = numbers.stream().findAny();
        System.out.println(optional1.get());

        boolean result = numbers.stream().anyMatch(n -> n%2 == 0);
        System.out.println(result);

        boolean result1 = numbers.stream().allMatch(n -> n%2 == 0);
        System.out.println(result1);

        boolean result2 = numbers.stream().noneMatch(n -> n%2 == 0);
        System.out.println(result2);
    }
}
