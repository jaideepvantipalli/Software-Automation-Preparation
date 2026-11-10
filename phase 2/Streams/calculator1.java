public interface calculator1 {
    void add(int a,int b);
}
class CalculatorImpl{
    public static void main(String[] args) {
        Calculator obj=(a,b)->{
            System.out.println(a+b);
        };
        obj.add(1,2);
    }
}
