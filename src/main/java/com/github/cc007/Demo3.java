package com.github.cc007;

import manifold.science.measures.Velocity;

import static manifold.science.util.UnitConstants.hr;
import static manifold.science.util.UnitConstants.km;
import static manifold.science.util.UnitConstants.mph;

/**
 * With manifold-ext, you can overload operators.
 * With manifold-props, you can use getters and setters as if they were fields.
 */
public class Demo3 {
    public static void main(String[] args) {
        Vehicle car = new Car();
        car.accelerate(100mph);
    }

    public interface Vehicle {
        void start();
        void accelerate(Velocity speed);
        String getName();
    }

    public static class Car implements Vehicle {
        private boolean isStarted = false;
        private Velocity speed = 0km / hr;

        @Override
        public void accelerate(Velocity delta) {
            if (!isStarted) {
                start();
            }
            // Here the plus operator is overloaded to add the speed to the current speed
            speed = speed + delta;
            // getters and setters can be accessed as if they were fields.
            // This works both for normal statements and (in the case of getters) string interpolation.
            System.out.println("$name accelerated to $speed");
        }

        @Override
        public void start() {
            isStarted = true;
            System.out.println("$name started");
        }

        @Override
        public String getName() {
            return "Car";
        }
    }
}
