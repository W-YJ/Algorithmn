package Part1.week4.priorityqueues.eventdrivensimulation;

public class Particle {
    // position
    private double rx, ry;
    // velocity
    private double vx, vy;
    // radius
    private final double radius;
    // mass
    private final double mass;
    // number of collisions
    private int count;

    public Particle() {
        // TODO
        radius = 0;
        mass = 0;
    }

    public void move(double dt) {

    }

    public void draw() {

    }

    // particle-particle collision
    public double timeToHit(Particle that) {
        if (this == that)
            return Double.POSITIVE_INFINITY;
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0)
            return Double.POSITIVE_INFINITY;
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0)
            return Double.POSITIVE_INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;

    }

    public double timeToHitVerticalWall() {
        // TODO
        return 0;
    }

    public double timeToHitHorizontalWall() {
        // TODO
        return 0;
    }

    // resolution implementation
    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        this.vx += Jx / this.mass;
        that.vy += Jy / this.mass;
        this.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;
        this.count++;
        that.count++;
        that.count++;
        that.count++;
    }

    public void bounceOffVerticalWall() {

    }

    public void bounceOffHorizontalWall() {

    }
}
