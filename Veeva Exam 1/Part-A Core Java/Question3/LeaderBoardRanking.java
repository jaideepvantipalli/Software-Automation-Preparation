import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Participant{
    private String name;
    private int problemsSolved;
    private int penalityTime;
    private int rating;
    public Participant(String name,int ProblemsSolved,int penalityTime,int rating){
        this.name = name;
        this.problemsSolved = ProblemsSolved;
        this.penalityTime = penalityTime;
        this.rating = rating;
    }
    public String getName(){
        return name;
    }
    public int getProblemsSolved(){
        return problemsSolved;
    }
    public int getPenalityTime(){
        return penalityTime;
    }
    public int getRating(){
        return rating;
    }
}

public class LeaderBoardRanking {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);

        List<Participant> participants = new ArrayList<>();
        System.out.println("Enter the no of participants want to add : ");

        int n=sc.nextInt();
        System.out.println("Enter the participant Details: ");
        System.out.println("Name\tProblems Solved\tPenality Time\tRating");
        for(int i=0;i<n;i++){
            String name=sc.next();
            int problemsSolved=sc.nextInt();
            int penalityTime=sc.nextInt();
            int rating=sc.nextInt();
            participants.add(new Participant(name,problemsSolved,penalityTime,rating));
        }

        List<Participant> leaderBoard = participants.stream()
                .sorted(Comparator.comparingInt(Participant::getProblemsSolved).reversed()
                                .thenComparingInt(Participant::getPenalityTime)
                                .thenComparing(Comparator.comparingInt(Participant::getRating).reversed())
                                .thenComparing(Participant::getName)
                )
                .collect(Collectors.toList());

        for(Participant participant:leaderBoard){
            System.out.println(participant.getName()+"\t"+ participant.getProblemsSolved()+"\t"+ participant.getPenalityTime()+"\t"+ participant.getRating());
        }
        sc.close();

    }
}
