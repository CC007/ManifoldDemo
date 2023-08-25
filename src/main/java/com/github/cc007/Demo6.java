package com.github.cc007;

import lombok.NoArgsConstructor;
import lombok.ToString;
import manifold.ext.props.rt.api.get;
import manifold.ext.props.rt.api.set;
import manifold.ext.props.rt.api.val;
import manifold.ext.props.rt.api.var;

import static manifold.ext.props.rt.api.PropOption.Private;

/**
 * With manifold-props, you can specify if a field should have a getter and/or setter.
 */
public class Demo6 {

    // added some self-explanatory lombok annotations to interact with the class
    @ToString
    @NoArgsConstructor
    public static class Properties {
        // the @get annotation specifies that a getter will be generated for this field
        @get String readOnlyField1 = "read only";
        // the @set annotation specifies that a setter will be generated for this field
        @set @get(Private) String writeOnlyField = "write only";

        // the @var annotation specifies that both a getter and a setter will be generated for this field
        @var String readWriteField = "read write";
        // the @val works the same as @get and was probably added for consistency with @var like in Kotlin
        @val String readOnlyField2 = "read only too";
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        System.out.println(properties);
        // as mentioned before, you can access getters as if they were fields
        System.out.println(properties.readOnlyField1);
        System.out.println(properties.readOnlyField2);
        System.out.println(properties.readWriteField);
        // and the same goes for setters
        properties.readWriteField = "foo";
        properties.writeOnlyField = "bar";
        System.out.println(properties);
    }

}
