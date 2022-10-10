package net.htlgkr.KohlbauerS190178.hue4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {


    List<Integer> intList = new ArrayList<>();
    final String FILENAME = "numbers.csv";
    final String SEPARATOR = ":";
    final String REGEX = "[0-9]+";

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.readFile(main.FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        main.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int divider = 0;
        int listParts = 0;
        while (true) {
            try {
                printMsgforDivider();
                divider = Integer.parseInt(scanner.nextLine());
                if (divider == 0) {
                    System.out.println("No division by 0");
                    continue;
                }
                printMsgforParts();
                listParts = Integer.parseInt(scanner.nextLine());

                if(listParts==0){
                    System.out.println("Can't calculate with 0 parts");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("invalid input!");
            }
        }
        Collection<List<Integer>> lists = createListParts(intList, listParts);
        int finalDivider = divider;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(listParts);
        //lists.forEach(l -> new Thread(new DividerCheckerRunnable(l, finalDivider)).start());
        lists.forEach(l -> executor.execute(new DividerCheckerRunnable(l, finalDivider)));
        executor.shutdown();
    }

    public Collection<List<Integer>> createListParts(List<Integer> intList, int amountParts) {
        AtomicInteger counter = new AtomicInteger();
        return intList.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / (intList.size() / amountParts))).values();
    }

    public void readFile(String filename) throws IOException {
        Files.lines(Paths.get(filename)).forEach((line) -> Arrays.stream(line.split(SEPARATOR)).filter(s -> s.matches(REGEX)).toList().forEach(string -> intList.add(Integer.parseInt(string))));
    }

    public void printMsgforDivider() {
        System.out.println("Enter a divider:");
    }

    public void printMsgforParts() {
        System.out.println("Enter in how many parts you want to calculate:");
    }
}
