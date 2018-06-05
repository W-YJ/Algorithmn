package Part1.week4.priorityqueues.eventdrivensimulation;

import edu.princeton.cs.algs4.StdDraw;

public class Ball {
    // position
    private double rx, ry;
    // velocity
    private double vx, vy;
    private final double radius;

    public Ball() {
        /*initialize position and velocity*/
        // TODO
        radius = 0;
    }

    public void move(double dt) {
        if ((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius)) {
            vx = -vx;
        }
        if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius)) {
            vy = -vy;
        }
        rx = rx + vx * dt;
        ry = ry + vy * dt;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }
}
