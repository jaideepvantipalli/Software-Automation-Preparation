import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class ShortestWord {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of strings want to add : ");
        int n=sc.nextInt();
        sc.nextLine();
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<=n;i++)
        {
            list.add(sc.nextLine().trim());
        }

        Optional<String> shortest = list.stream()
                .sorted(Comparator.comparingInt(String::length))
                        .findFirst();

        System.out.println("Shortest Word : "+shortest.orElse("not Found!"));
    }
}