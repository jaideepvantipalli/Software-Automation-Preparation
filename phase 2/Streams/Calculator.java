public interface Calculator {

    void add(int a, int b);
}

class CalculatorImpl2 implements Calculator {
    @Override
    public void add(int a, int b) {
        System.out.println(a+b);
    }

    public static void main(String[] args) {
        Calculator c = new CalculatorImpl2();
        c.add(1,2);
    }
}
