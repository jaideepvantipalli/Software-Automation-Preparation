import java.util.Arrays;
import java.util.List;

public class IntermediateOperationsDemo {
    public static void main(String[] args) {
        List<Integer> obj= Arrays.asList(1,2,3,4,5,6,7,8,9,10,1);

        //filter
        obj.stream().filter(n -> n%2 ==0).forEach(System.out::print);
        System.out.println();

        //map
        obj.stream().map(n -> n*n)
                .forEach(System.out::print);
        System.out.println();
        //sorted
        obj.stream().sorted().forEach(System.out::print);
        System.out.println();
        //distinct
        obj.stream().distinct().forEach(System.out::print);
        System.out.println();

        //limit
        obj.stream().limit(5).forEach(System.out::print);
        System.out.println();
        //skip
        obj.stream().skip(2).forEach(System.out::print);
        System.out.println();
        //peek
        obj.stream().peek(n -> System.out.println("Original: " + n)).map(n->n*2).forEach(System.out::println);;

    }
}
