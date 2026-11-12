import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CaseStudy2 {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(
                10, 20, 30, 20, 40, 10, 10, 50, 20, 30
        );

        List<Integer> list2 = Arrays.asList(
                20, 30, 40, 40, 50, 60, 20, 20, 70, 80
        );
        //A) sum,avg,max,min
        IntSummaryStatistics stats = list1.stream().mapToInt(Integer::intValue).summaryStatistics();
        IntSummaryStatistics stats2 = list2.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("statistics of list 1 :");
        System.out.println("Min : "+stats.getMin());
        System.out.println("Max : "+stats.getMax());
        System.out.println("Sum : "+stats.getSum());
        System.out.println("Average : "+stats.getAverage());

        System.out.println("statistics of list 2 :");
        System.out.println("Min : "+stats2.getMin());
        System.out.println("Max : "+stats2.getMax());
        System.out.println("Sum : "+stats2.getSum());
        System.out.println("Average : "+stats2.getAverage());

        //B) find k most frequent elements from list 1 and list2

        System.out.println("K most frequent element in list 1 :");
        Map<Integer,Long> frequency= list1.stream()
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        ));

        int k=2;
        List<Map.Entry<Integer,Long>> result=frequency.entrySet()
                        .stream()
                                .sorted((e1,e2)->{
                                    int freq=Long.compare(e2.getValue(),e1.getValue());
                                    if(freq==0){
                                        return Integer.compare(e1.getKey(),e2.getKey());
                                    }
                                    return freq;
                                })
                                .limit(k)
                                .collect(Collectors.toList());

        System.out.println("in list 1 : "+result);
        System.out.println("K most frequent element in list 2 :");
        Map<Integer,Long> frequency2 = list2.stream().collect(Collectors.groupingBy(
                Function.identity(),Collectors.counting()
        ));

        List<Map.Entry<Integer,Long>> result2 = frequency2.entrySet().stream().sorted(
                        (e1,e2)->{
                            int freq=Long.compare(e2.getValue(),e1.getValue());
                            if(freq==0){
                                return Integer.compare(e1.getKey(),e2.getKey());
                            }
                            return freq;
                        }
                )
                .limit(k)
                .collect(Collectors.toList());
        System.out.println("in list 2 : "+result2);

        //C)second most freq element
        Map<Integer,Long> freq = list1.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        Optional<Map.Entry<Integer,Long>> secondMost=freq.entrySet().stream()
        .sorted((e1,e2)->{
            int tempfreq=Long.compare(e2.getValue(),e1.getValue());
            if(tempfreq==0){
                return Integer.compare(e1.getKey(),e2.getKey());
            }
            return tempfreq;
        }).skip(1)
          .findFirst();

        System.out.println("Second most Element of list 1 :"+secondMost.orElse(null));

        Map<Integer,Long> freq2 = list2.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        Optional<Map.Entry<Integer,Long>> secondMost2=freq2.entrySet().stream()
                .sorted((e1,e2)->{
                    int tempfreq2=Long.compare(e2.getValue(),e1.getValue());
                    if(tempfreq2==0){
                        return Integer.compare(e1.getKey(),e2.getKey());
                    }
                    return tempfreq2;
                }).skip(1)
                .findFirst();

        System.out.println("Second most Element of list 2 :"+secondMost2.orElse(null));

        //D) flattening the list
        System.out.println("Flatting 2 list:");
        List<Integer> mergedList = Stream.of(list1,list2)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(mergedList);

        //E) common elements in two lists
        System.out.println("Common Elements :");
        List<Integer> commonele=list1.stream()
                .filter(list2::contains)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(commonele);
    }
}
