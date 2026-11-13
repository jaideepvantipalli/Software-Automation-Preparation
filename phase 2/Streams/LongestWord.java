import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class LongestWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of strings want to add : ");
        int n=sc.nextInt();
        ArrayList<String> list=new ArrayList<>();
        for(int i=1;i<=n;i++)
        {
            list.add(sc.nextLine().trim());
        }

        Optional<String> longest = list.stream()
                .max(Comparator.comparing(String::length));

        System.out.println("Longest Word : "+longest.orElse("not Found!"));
    }
}
