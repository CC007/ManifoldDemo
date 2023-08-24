package com.github.cc007;

/**
 * With manifold-strings, you can use string interpolation to insert variables into strings.
 */
public class Demo1 {

    public static void main(String[] args) {
        int a = 1;
        // Here the $a is replaced with the value of a
        // This is the same as System.out.println("The value of a is " + a);
        System.out.println("The value of a is $a");
    }
}
