import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void main(String[] args){

        //creation
        List<String> list= Arrays.asList("A","B","C");

        //using .stream()
        Stream<String> stream = list.stream();
        stream.forEach(System.out::print);

        //using Arrays.stream(collection obj)
        String arr[]={"a","b","c"};
        Stream<String> stream2=Arrays.stream(arr);

        stream2.forEach(System.out::print);

        //using stream.of()
        Stream<String> stream1 = Stream.of("a","b","c");
        stream1.forEach(System.out::print);

        //using

        Stream.iterate(1,n->n+1)
                .limit(5)
                .forEach(System.out::print);
    }
}
