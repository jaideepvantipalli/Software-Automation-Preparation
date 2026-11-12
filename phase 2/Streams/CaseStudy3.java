import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CaseStudy3 {
    public static boolean isPrime(int n){
        if(n<=1) return false;
        for(int i=2;i <= Math.sqrt(n);i++){
            if(n%i==0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<String> words = Arrays.asList(
                "Java","Python","Spring","hibernate","SQL","MongoDb"
        );

        //convert each string into a string length pair
        System.out.println("String and its Length :");
        Map<String,Integer> strLength=words.stream().collect(Collectors.toMap(
                Function.identity(),String::length
        ));

        strLength.forEach((word,length)->System.out.println(word + " - " + length));

        words.stream().map(s->s.length()).forEach(System.out::println);

        //Find all prime numbers
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 29);

        List<Integer> primes=numbers.stream()
                .filter(CaseStudy3::isPrime)
                .collect(Collectors.toList());
        System.out.println("prime Numbers :");
        primes.forEach(System.out::println);

        //non primes
        List<Integer> nonPrimes = numbers.stream()
                .filter(n->!isPrime(n))
                .collect(Collectors.toList());
        System.out.println("non prime numbers : ");
        nonPrimes.forEach(System.out::println);

        //First Repeating Characters
        String str="programming";

        List<Character> repeating= str.chars()
                .mapToObj(c->(char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1 )
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("repeating :"+repeating);

        //First Repeating Character in a string
        System.out.println("Enter the Input string");
        String ip=sc.next();
        Map<Character,Long> freq = ip.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        Optional<Character> firstRepeatingCharacter = ip.chars()
                .mapToObj(c->(char)c)
                .filter(ch->freq.get(ch) > 1)
                .findFirst();


        firstRepeatingCharacter.ifPresent(System.out::println);

    }
}
