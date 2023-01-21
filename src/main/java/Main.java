import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        System.out.println("ready");
        Scanner scanner = new Scanner(System.in);
        CashRegister register = new CashRegister();
        while(run){
            String line = scanner.nextLine();
            run = register.parseInput(line);
        }
    }
}
