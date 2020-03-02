import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split(" ");

        List<Integer> numbers = new ArrayList<>();

        for (String part: parts) {
            numbers.add(Integer.parseInt(part));
        }

        Integer check = scanner.nextInt();

        List<Integer> closest = new ArrayList<>();
        int difference = Math.abs(check - numbers.get(0));

        for (Integer i: numbers) {
            if (Math.abs(check - i) < difference) {
                difference = Math.abs(check - i);
            }
        }

        for (Integer i: numbers) {
            if (Math.abs(check - i) == difference) {
                closest.add(i);
            }
        }

        Collections.sort(closest);

        for (Integer c: closest) {
            System.out.print(c + " ");
        }
    }
}