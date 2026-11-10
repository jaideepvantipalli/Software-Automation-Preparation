public class Student {
    private int rollNumber;
    private int age;
    private String name;
    private double m1,m2,m3,m4,m5;
    private String grade;

    public Student(int rollNumber,int age,String name,
                   double m1,double m2,double m3,double m4,double m5) {
        this.rollNumber = rollNumber;
        this.age = age;
        this.name=name;
        this.m1=m1;
        this.m2=m2;
        this.m3=m3;
        this.m4=m4;
        this.m5=m5;
        this.grade=calculateGrade();
    }
    public int getRollNumber() {
        return rollNumber;
    }

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }

    public String calculateGrade(){
        double avg=(m1+m2+m3+m4+m5)/5.0;
        if(avg <=100 && avg >91 ) return "S";
        else if(avg <=90 && avg >81 ) return "A";
        else if(avg <=80 && avg >71 ) return "B";
        else if(avg <=70 && avg >61 ) return "C";
        else if(avg <=60 && avg >51 ) return "D";
        else if(avg <=50 && avg >41 ) return "E";
        else return "F";
    }

    public String toFileRecord(){
        return rollNumber +"|"+age+"|"+name+"|"+m1+"|"+m2+"|"+m3+"|"+m4+"|"+m5+"|"+grade;
    }
    public static Student fromFileRecord(String record){
        String[] data=record.split("\\|");
        return new Student(
                  Integer.parseInt(data[0]),
                  Integer.parseInt(data[1]),
                  data[2],
                  Double.parseDouble(data[3]),
                  Double.parseDouble(data[4]),
                Double.parseDouble(data[5]),
                Double.parseDouble(data[6]),
                Double.parseDouble(data[7])
        );
    }

    public void displayDetails(){
        System.out.println("--------------------");
        System.out.println("Roll Number: "+rollNumber);
        System.out.println("Age: "+age);
        System.out.println("Name: "+name);
        System.out.println("M1: "+m1);
        System.out.println("M2: "+m2);
        System.out.println("M3: "+m3);
        System.out.println("M4: "+m4);
        System.out.println("M5: "+m5);
        System.out.println("Grade: "+grade);
        System.out.println("--------------------");
    }
}
