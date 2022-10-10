package net.htlgkr.KohlbauerS190178.hue4;

import java.util.List;

public class DividerCheckerRunnable implements Runnable{

    List<Integer> intList;
    int divider;

    public DividerCheckerRunnable(List<Integer> intList, int divider) {
        this.intList = intList;
        this.divider = divider;
    }

    @Override
    public void run() {
        intList.stream().filter(i->i%divider==0).forEach(System.out::println);
    }
}
