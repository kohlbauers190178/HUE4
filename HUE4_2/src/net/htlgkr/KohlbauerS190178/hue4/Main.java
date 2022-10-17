package net.htlgkr.KohlbauerS190178.hue4;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) {
        Main main = new Main();
        main.beispiel2();
        main.beispiel3();
    }

    public void beispiel2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Beispiel 1:\n");
        System.out.println("Enter a integer n:");
        int n = 0;
        try {
            n = scanner.nextInt();
            if (n <= 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("invalid input ");
            return;
        }

        double callableAmount = Math.ceil((double) n / 100);

        int temp = n;

        List<CalculatingCallable> calculatingCallableList = new ArrayList<>();

        for (int i = 0; i < callableAmount; i++) {

            CalculatingCallable calculatingCallable;
            if (temp > 100) {
                calculatingCallable = new CalculatingCallable(i * 100 + 1, i * 100 + 100);
                temp -= 100;
            } else {
                calculatingCallable = new CalculatingCallable((i) * 100 + 1, (i) * 100 + temp);
            }
            calculatingCallableList.add(calculatingCallable);
        }

        List<Integer> sums = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool((int) callableAmount);
        calculatingCallableList.forEach(c -> {
            try {
                sums.add(executor.submit(c).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();

        System.out.println(sums.stream().reduce(0, Integer::sum));
    }

    public void beispiel3() {
        System.out.println("Beispiel 3:\n");

        List<String> strings = Arrays.asList("hallo", "aa", "jj", "1234", "test", "schoenerString", "", "");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);



        System.out.println("Empty Strings:\n"+JavaStreamsTester.getCountEmptyString(strings)+"\n");
        System.out.println("Strings with more than 3 characters:\n"+JavaStreamsTester.getCountLength3(strings)+"\n");

        System.out.println("List without empty strings:");
        JavaStreamsTester.deleteEmptyStrings(strings).forEach(System.out::println);
        System.out.println();

        System.out.println("Merged String:\n"+JavaStreamsTester.getMergedString(strings,";")+"\n");

        System.out.println("Squares of each number:\n");
        JavaStreamsTester.getSquares(numbers).forEach(System.out::println);
        System.out.println();

        System.out.println("Max number:\n"+JavaStreamsTester.getMax(numbers)+"\n");
        System.out.println("Min number:\n"+JavaStreamsTester.getMin(numbers)+"\n");

        System.out.println("Sum of numbers:\n"+JavaStreamsTester.getSum(numbers)+"\n");
        System.out.println("Average of numbers:\n"+JavaStreamsTester.getAverage(numbers)+"\n");


    }

}
