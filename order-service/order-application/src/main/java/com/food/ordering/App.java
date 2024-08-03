package com.food.ordering;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);

        list.stream().filter(i -> i%2 == 0).forEach(System.out::println);

        System.out.println( "Hello World!" );
    }
}
