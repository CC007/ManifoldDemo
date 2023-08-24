package com.github.cc007;

import manifold.science.measures.Velocity;

import static manifold.science.util.UnitConstants.hr;
import static manifold.science.util.UnitConstants.km;
import static manifold.science.util.UnitConstants.mph;

/**
 * With manifold-science, you can use units of measure in your code.
 */
public class Demo2 {
    public static void main(String[] args) {
        Vehicle car = new Car();
        // Here the mph unit is used to create a Velocity object
        car.accelerate(100mph);
    }

    public interface Vehicle {
        void start();
        void accelerate(Velocity speed);
        String getName();
    }

    public static class Car implements Vehicle {
        private boolean isStarted = false;
        // You can also combine units of measure
        private Velocity speed = 0km / hr;

        @Override
        public void accelerate(Velocity speed) {
            if (!isStarted) {
                start();
            }
            this.speed = this.speed.plus(speed);
            // You can also use string interpolation to call methods on objects or access fields.
            System.out.println("${getName()} accelerated to ${this.speed}");
        }

        @Override
        public void start() {
            isStarted = true;
            System.out.println("${getName()} started");
        }

        @Override
        public String getName() {
            return "Car";
        }
    }
}
