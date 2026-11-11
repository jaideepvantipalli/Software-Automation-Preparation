
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;
public class ProblemsOnStreams {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        List<String> list= Arrays.asList("java","python","C","CPP","java script","Ruby","ABAP",
                "OracleDB", "MySql","Postgres","MongoDB","REDIS",
                "Windows","Linux","MacOS","Fedora",
                "ChatGPT","ClaudeAi","CodeGPT","COPILOT","GEMINI"
        );

        while(true){
            System.out.println("\n========== JAVA STREAM API MENU ==========");
            System.out.println("1.  Starts with a specific letter");
            System.out.println("2.  Starts with J and ends with A");
            System.out.println("3.  Starts with a specific letter and uppercase");
            System.out.println("4.  Starts with a specific letter and distinct");
            System.out.println("5.  Starts with a specific letter, distinct and uppercase");
            System.out.println("6.  Starts with a specific letter and sorted");
            System.out.println("7.  Starts with a specific letter, distinct, sorted and uppercase");
            System.out.println("8.  Count distinct elements starting with a specific letter");
            System.out.println("9.  Find first element starting with a specific letter");
            System.out.println("10. Find any element starting with a specific letter");
            System.out.println("11. Check whether any element starts with a specific letter");
            System.out.println("12. Check whether all elements start with a specific letter");
            System.out.println("13. Print all elements");
            System.out.println("0.  Exit");
            System.out.print("\nEnter your choice: ");

            int choice = input.nextInt();
            input.nextLine();
            switch(choice){
                case 1:
                    System.out.print("Enter a letter: ");
                    String ip1=input.nextLine().toLowerCase();
                    list.stream().filter(s -> s.toLowerCase().startsWith(String.valueOf(ip1.charAt(0)))).forEach(System.out::println);
                    break;
                case 2 :
                    list.stream().filter(s -> s.toLowerCase().startsWith("j") && s.toLowerCase().endsWith("a")).forEach(System.out::println);
                    break;
                case 3 :
                    System.out.print("Enter a letter: ");
                    String ip3=input.nextLine().toLowerCase();
                    list.stream().filter(s-> s.toLowerCase().startsWith(String.valueOf(ip3.charAt(0)))).map(String::toUpperCase).forEach(System.out::println);
                    break;
                case 4 :
                    System.out.print("Enter a letter: ");
                    String ip4=input.nextLine().toLowerCase();
                    list.stream().filter(s->s.toLowerCase().startsWith(String.valueOf(ip4.charAt(0)))).distinct().forEach(System.out::println);
                    break;
                case 5 :
                    System.out.print("Enter a letter: ");
                    String ip5=input.nextLine().toLowerCase();
                    list.stream().filter(s->s.toLowerCase().startsWith(String.valueOf(ip5.charAt(0)))).distinct().map(String::toUpperCase).forEach(System.out::println);
                    break;
                case 6 :
                    System.out.print("Enter a letter: ");
                    System.out.print("Enter a letter: ");
                    String ip6=input.nextLine().toLowerCase();
                    list.stream().filter(s->s.toLowerCase().startsWith(String.valueOf(ip6.charAt(0)))).sorted().forEach(System.out::println);
                    break;
                case 7 :
                    System.out.print("Enter a letter: ");
                    String ip7=input.nextLine().toLowerCase();
                    list.stream().filter(s->s.toLowerCase().startsWith(String.valueOf(ip7.charAt(0)))).distinct().sorted().map(String::toUpperCase).forEach(System.out::println);
                    break;
                case 8 :
                    System.out.print("Enter a letter: ");
                    String ip8=input.nextLine().toLowerCase();
                    long cnt=list.stream().filter(s->s.toLowerCase().startsWith(String.valueOf(ip8.charAt(0)))).distinct().count();
                    break;
                case 9 :
                    System.out.print("Enter a letter: ");
                    String ip9=input.nextLine().toLowerCase();
                    Optional<String> optional = list.stream().filter(s->s.toLowerCase().startsWith(String.valueOf(ip9.charAt(0)))).findFirst();
                    System.out.println(optional.isPresent()?optional.get():"None");
                    break;
                case 10 :
                    System.out.print("Enter a letter: ");
                    String ip10=input.nextLine().toLowerCase();
                    Optional<String> optional1 = list.stream().filter(s->s.toLowerCase().startsWith(String.valueOf(ip10.charAt(0)))).findAny();
                    System.out.println(optional1.isPresent()?optional1.get():"None");
                    break;
                case 11 :
                    System.out.print("Enter a letter: ");
                    String ip11=input.nextLine().toLowerCase();
                    boolean res=list.stream().filter(s->s.toLowerCase().startsWith(String.valueOf(ip11.charAt(0))))
                            .allMatch(s->s.toLowerCase().startsWith(String.valueOf(ip11.charAt(0))));
                    System.out.println("All Matched with "+ip11.charAt(0) +" : "+res);
                    break;
                case 12 :
                    System.out.print("Enter a letter: ");
                    System.out.print("Enter a letter: ");
                    String ip12 = input.nextLine().toLowerCase();

                    boolean res1 = list.stream()
                            .anyMatch(s -> s.toLowerCase().startsWith(String.valueOf(ip12.charAt(0))));

                    System.out.println("Any element starts with " + ip12.charAt(0) + " : " + res1);
                    break;
                case 13 :
                    list.stream().forEach(System.out::println);
                    break;
                case 0 :
                    input.close();
                    System.exit(0);
                default: {
                    System.out.println("Invalid choice! Please select a valid option.");
                }

            }
        }
    }
}
