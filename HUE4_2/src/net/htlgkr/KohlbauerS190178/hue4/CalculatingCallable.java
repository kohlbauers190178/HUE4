package net.htlgkr.KohlbauerS190178.hue4;

import java.util.concurrent.Callable;
import java.util.stream.IntStream;

public class CalculatingCallable implements Callable<Integer> {

    int bottom;
    int top;

    public CalculatingCallable(int bottom, int top) {
        this.bottom = bottom;
        this.top = top;
    }

    @Override
    public Integer call() throws Exception {
        return IntStream.range(bottom,top+1).reduce(0, Integer::sum);
    }
}
