package Part1.week4.priorityqueues.eventdrivensimulation;

import edu.princeton.cs.algs4.MinPQ;

public class CollisionSystem {

    private class Event implements Comparable<Event> {
        // time of event
        private double time;
        // particles involved in event
        private Particle a, b;
        // collision counts for a and b
        private int countA, countB;

        // create event
        public Event(double t, Particle a, Particle b) {

        }

        // ordered by time
        public int compareTo(Event that) {
            double dt = this.time - that.time;
            if (dt > 0)
                return 1;
            else if (dt == 0)
                return 0;
            else
                return -1;

        }

        // invalid if intervening collision
        public boolean isValid() {
            // TODO
            return false;

        }
    }

    // the priority queue
    private MinPQ<Event> pq;
    // simulation clock time
    private double t = 0.0;
    // the array of particles
    private Particle[] particles;

    public CollisionSystem(Particle[] particles) {

    }

    // add to PQ all particle-wall and particle-particle collisions involving this particle
    private void predict(Particle a) {
        if (a == null)
            return;
        for (int i = 0; i < N; i++) {
            double dt = a.timeToHit(particles[i]);
            pq.insert(new Event(t + dt, a, particles[i]));
        }
        pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
        pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
    }

    private void redraw() {

    }

    public void simulate() {
        // initialize PQ with collision events and redraw event
        pq = new MinPQ<Event>();
        for (int i = 0; i < N; i++)
            predict(particles[i]);
        pq.insert(new Event(0, null, null));

        while (!pq.isEmpty()) {
            // get next event
            Event event = pq.delMin();
            if (!event.isValid())
                continue;
            Particle a = event.a;
            Particle b = event.b;

            // update positions and time
            for (int i = 0; i < N; i++)
                particles[i].move(event.time - t);
            t = event.time;

            //process event
            if (a != null && b != null)
                a.bounceOff(b);
            else if (a != null && b == null)
                a.timeToHitVerticalWall();
            else if (a == null && b != null)
                b.bounceOffHorizontalWall();
            else if (a == null && b == null)
                redraw();

            // predict new events based on changes
            predict(a);
            predict(b);
        }


    }
}
