import java.util.Scanner;

public class crash {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("this is not a number");
            }
            System.out.println("Your number is : "+ scanner.nextLine());
        }
    }
}
