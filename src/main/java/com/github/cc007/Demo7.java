package com.github.cc007;

import lombok.ToString;
import manifold.ext.props.rt.api.set;
import manifold.ext.props.rt.api.var;

import static manifold.ext.props.rt.api.PropOption.Package;
import static manifold.ext.props.rt.api.PropOption.Private;
import static manifold.ext.props.rt.api.PropOption.Protected;

/**
 * With manifold-props, you can also specify if a setter should be private or protected.
 */
public class Demo7 {

    @ToString
    public static class Properties {
        // public modifier needed here, see issue: https://github.com/manifold-systems/manifold/issues/483
        @var @set(Protected) String protectedSet = "can only set in class and subclasses and other classes in the same package";
        @var @set(Package) String packagePrivateSet = "can only set in class and other classes in the same package";
        @var @set(Private) String privateSet = "can only set in class itself";

        public Properties() {
            protectedSet = "protected setter used in Properties";
            packagePrivateSet = "package-private setter used in Properties";
            privateSet = "private setter used in Properties";
        }
    }

    public static class SubProperties extends Properties {
        public SubProperties() {
            protectedSet = "protected setter used in SubProperties";
            packagePrivateSet = "package-private setter used in SubProperties";
            // privateSet = "private setter used in SubProperties"; <-- this technically would compile, but it only because it's a nested class
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        System.out.println(properties);
        SubProperties subProperties = new SubProperties();
        System.out.println(subProperties);
    }
}
