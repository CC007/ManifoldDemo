package com.github.cc007;

import manifold.ext.rt.api.auto;

import java.time.LocalDateTime;

/**
 * With manifold-tuple you can use tuples in Java.
 */
public class Demo8_Tuples {
    public static void main(String[] args) {
        // Here you see a tuple with unnamed items
        // You'll have to use var or auto to declare a tuple
        var tupleWithUnnamedItems = (1, 2, 3, 4);
        // tuples with unnamed items can be accessed with the item1, item2, etc.
        tupleWithUnnamedItems.item1 = 5;
        System.out.println(tupleWithUnnamedItems.item1);

        // Here you see a tuple with named items
        var tupleWithNamedItems = (name: "Tom", age: 42);
        // which can be accessed with the name of the item
        tupleWithNamedItems.name = "Jerry";
        System.out.println("${tupleWithNamedItems.name} is ${tupleWithNamedItems.age} years old");

        // You can iterate over tuples with unnamed items
        for (var item : tupleWithUnnamedItems) {
            System.out.println("${item.name} has value ${item.value}");
        }

        // and also over tuples with named items
        for (var item : tupleWithNamedItems) {
            System.out.println("${item.name} has value ${item.value}");
        }

        // here's a tuple that was returned from a method
        var tupleItems = returnTuple();

        // Unfortunately you can't use tuple destructuring with manifold (...yet! See: https://github.com/manifold-systems/manifold/issues/485)
        // var (s, i, d, ldt) = tupleItems;

        // So you'll have to use the item1, item2, etc. to access the items
        String s = tupleItems.item1;
        int i = tupleItems.item2;
        double d = tupleItems.item3;
        LocalDateTime ldt = tupleItems.item4;
        System.out.println("s = $s, i = $i, d = $d, ldt = $ldt");

        // or directly, which makes the line a bit longer
        System.out.println("s = ${tupleItems.item1}, i = ${tupleItems.item2}, d = ${tupleItems.item3}, ldt = ${tupleItems.item4}");

    }

    // Here's how you return a tuple from a method. Here you can't use var. You'll have to use auto.
    private static auto returnTuple() {
        // the brackets are optional for return statements
        return "foo", 42, 13.37, (LocalDateTime.now());
    }
}
