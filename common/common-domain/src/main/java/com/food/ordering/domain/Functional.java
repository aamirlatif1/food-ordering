package com.food.ordering.domain;

import java.util.function.*;

public class Functional {

    static void runFunction(Function<String, String> function){
        String result = function.apply("Aamir Latif");
        System.out.println(result);
    }

    static void runConsumer(Consumer<String> consumer) {
        consumer.accept("Aamir");
    }

    static void runSupplier(Supplier<String> supplier) {
        String result = supplier.get();
        System.out.println(result);
    }

    static void runBiFunction(BiFunction<String, String, String> biFunction) {
        String result = biFunction.apply("Aamir", "Latif");
        System.out.println(result);
    }

    static void runBinaryOperator(BinaryOperator<String> binaryOperator) {
        String result = binaryOperator.apply("Aamir", "Latif");
        System.out.println(result);
    }

    public static void main(String[] args) {

        runFunction(v -> v.toUpperCase());

        runBiFunction((f, s) -> "Hello Mr. "+f+" "+s);

        runConsumer(a -> System.out.println(a));

        runSupplier(() -> "Aamir Latif");

        runBinaryOperator((f, s) -> String.format("Hi, I am %s, %s. Who are you?",s, f));

    }
}
