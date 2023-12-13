package practice;

import java.util.stream.IntStream;

public class lambda {

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach((int value) -> System.out.println(value));

    }
}
