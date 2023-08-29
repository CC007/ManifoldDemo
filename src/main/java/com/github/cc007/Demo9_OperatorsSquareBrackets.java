package com.github.cc007;

import lombok.Builder;
import manifold.ext.props.rt.api.var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * With manifold-ext, you can use square brackets to access elements of collections.
 * You can also define a get and set method for your class to use square brackets on its objects.
 */
public class Demo9_OperatorsSquareBrackets {

    private static void collections() {
        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        list[0] = "d";
        System.out.println(list[0]);
        Map<String, String> map = new HashMap<>(Map.of("a", "b", "c", "d"));
        map["a"] = "e";
        System.out.println(map["a"]);
    }

    /**
     * This class is a bit more complex. It has a get and set method that can be used to access the fields of the class.
     */
    @Builder
    static class Name {
        @var String firstName;
        @var String[] middleNames;
        @var String lastNamePrefix;
        @var String lastName;

        // This get method will allow you to use square brackets on objects of this class to access certain values
        // These can be mapped to fields of the class, but you can also use it to access other values
        public String get(String fieldName) {
            switch (fieldName) {
                case "firstName":
                    return firstName;
                case "middleNames":
                    return String.join(" ", middleNames);
                case "lastName":
                    return lastNamePrefix + " " + lastName;
                default:
                    throw new IllegalArgumentException("Unknown field name: " + fieldName);
            }
        }

        // This set method will allow you to use square brackets on objects of this class to set certain values
        // Note that the return type is the same as the value parameter type. Using void won't enable you to use of square brackets.
        public String set(String fieldName, String value) {
            String old = get(fieldName);
            switch (fieldName) {
                case "firstName" -> firstName = value;
                case "middleNames" -> middleNames = value.split(" ");
                case "lastName" -> {
                    String[] parts = value.split(" ");
                    lastName = parts[parts.length - 1];
                    String[] prefixParts = Arrays.copyOf(parts, parts.length - 1);
                    lastNamePrefix = String.join(" ", prefixParts);
                }
                default -> throw new IllegalArgumentException("Unknown field name: " + fieldName);
            }
            return old;
        }

        // You can define multiple get methods, so long as they have different parameter types
        public String get(int index) {
            if (index == 0) {
                return firstName;
            } else if (index <= middleNames.length) {
                return middleNames[index - 1];
            } else if (index == middleNames.length + 1) {
                return lastNamePrefix;
            } else if (index == middleNames.length + 2) {
                return lastName;
            } else {
                throw new ArrayIndexOutOfBoundsException("Unknown index: " + index);
            }
        }

        // for such an index based get method, it would come in handy to specify a length method
        // that way you don't need to catch the ArrayIndexOutOfBoundsException
        public int getLength() {
            return middleNames.length + 3;
        }
    }

    private static void selfDefined() {
        Name name = Name.builder()
            .firstName("Piet")
            .middleNames(new String[]{"Jan", "Thomas"})
            .lastNamePrefix("van der")
            .lastName("Geest")
            .build();

        System.out.println(name.firstName);
        System.out.println(Arrays.toString(name.middleNames));
        System.out.println(name.lastNamePrefix);
        System.out.println(name.lastName);

        // if the get method above is defined properly, you can use square brackets to access data from the object
        System.out.println(name["firstName"] + " " + name["middleNames"] + " " + name["lastName"]);

        // if the set method above is defined properly, you can use square brackets to set data on the object
        name["firstName"] = "Pieter";
        name["middleNames"] = "Johannes Tom";
        name["lastName"] = "in 't Gooi";

        System.out.println(name.firstName);
        System.out.println(Arrays.toString(name.middleNames));
        System.out.println(name.lastNamePrefix);
        System.out.println(name.lastName);
        System.out.println(name["firstName"] + " " + name["middleNames"] + " " + name["lastName"]);

        // and since an index based get is defined, you can also use square brackets to access data from the object
        for (int i = 0;i < name.length;i++) {
            System.out.println(name[i]);
        }
    }

    public static void main(String[] args) {
        collections();
        selfDefined();
    }
}
