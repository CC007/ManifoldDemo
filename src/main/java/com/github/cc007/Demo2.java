package com.github.cc007;

import manifold.science.measures.Velocity;

import static manifold.science.util.UnitConstants.hr;
import static manifold.science.util.UnitConstants.km;
import static manifold.science.util.UnitConstants.mph;

/**
 * You can also use string interpolation to call methods on objects or access fields.
 * With manifold-science, you can use units of measure in your code.
 */
public class Demo2 {
    public static void main(String[] args) {
        Vehicle car = new Car();
        car.accelerate(100 mph);
    }

    public interface Vehicle {
        void start();
        void accelerate(Velocity speed);
        String getName();
    }

    public static class Car implements Vehicle {
        private boolean isStarted = false;
        private Velocity speed = 0 km/hr;

        @Override
        public void start() {
            isStarted = true;
            System.out.println("${getName()} started");
        }

        @Override
        public void accelerate(Velocity speed) {
            if (!isStarted) {
                start();
            }
            this.speed = this.speed + speed;
            System.out.println("${getName()} accelerated to ${this.speed}");
        }

        @Override
        public String getName() {
            return "Car";
        }
    }
}
