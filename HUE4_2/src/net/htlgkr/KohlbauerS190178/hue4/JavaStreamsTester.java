package net.htlgkr.KohlbauerS190178.hue4;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JavaStreamsTester {

    public static int getCountEmptyString(List<String> strings){
        return (int) strings.stream().filter(String::isEmpty).count();
    }

    public static int getCountLength3(List<String> strings){
        return (int) strings.stream().filter(s->s.length()>=3).count();
    }

    public static List<String> deleteEmptyStrings(List<String> strings){
        return strings.stream().filter(s-> !(s.isEmpty())).collect(Collectors.toList());
    }

    public static String getMergedString(List<String> strings, String separator){
        return strings.stream().map(Objects::toString).collect(Collectors.joining(separator));
    }

    public static List<Integer> getSquares(List<Integer> numbers){
        return numbers.stream().map(i->i*i).collect(Collectors.toList());
    }

    public static int getMax(List<Integer> numbers){
        return numbers.stream().max(Integer::compareTo).get();
    }

    public static int getMin(List<Integer> numbers){
        return numbers.stream().min(Integer::compareTo).get();
    }

    public static int getSum(List<Integer> numbers){
        return numbers.stream().reduce(0,Integer::sum);
    }

    public static int getAverage(List<Integer> numbers){
        return (int) numbers.stream().mapToInt(Integer::intValue).average().getAsDouble();
    }
}
