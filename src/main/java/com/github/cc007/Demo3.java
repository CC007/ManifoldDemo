package com.github.cc007;

import lombok.AllArgsConstructor;
import manifold.ext.delegation.rt.api.link;
import manifold.science.measures.Velocity;

import static manifold.science.util.UnitConstants.hr;
import static manifold.science.util.UnitConstants.km;
import static manifold.science.util.UnitConstants.mph;

/**
 * With manifold-delegation, you can use the link annotation to delegate methods to other classes.
 * Note that Car is not annotated with @part. Because of this the {@link Vehicle#getName()} call in {@link Car#start()} and {@link Car#accelerate(Velocity)} is unaware of the implementation in {@link Tesla}.
 */
public class Demo3 {
    public static void main(String[] args) {
        Vehicle car = new Tesla(new Car());
        car.accelerate(100mph);
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

    @AllArgsConstructor
    public static class Tesla implements Vehicle {
        @link
        Car car;

        @Override
        public String getName() {
            return "Tesla";
        }
    }
}
