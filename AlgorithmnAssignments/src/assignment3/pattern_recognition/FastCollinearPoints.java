package assignment3.pattern_recognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private final List<LineSegment> linesList;

    // find all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException();
        }

        int len = points.length;

        for (int i = 0; i < len; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
        }

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
        }

        linesList = new ArrayList<LineSegment>();

        Point[] copy = Arrays.copyOf(points, len);
        Arrays.sort(copy, 0, len);

        for (int i = 0; i < len-3 ; i++) {

            // Think of p as the origin.
            Point origin = copy[i];
            Point[] others = new Point[len - i - 1];
            double[] slopes = new double[len - i - 1];

            for (int j = 0; j < len - i - 1; j++) {
                others[j] = copy[i + j + 1];
            }

            // For each other point q, determine the slope it makes with p.
            for (int j = 0; j < others.length; j++) {
                slopes[j] = copy[i].slopeTo(others[j]);
            }

            // Sort the points according to the slopes they makes with p.
            Arrays.sort(others, origin.slopeOrder());

            // Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.
            // If so, these points, together with p, are collinear.
            int count = 0;
            for (int j = 0; j < slopes.length - 1; j++) {
                if (Double.compare(slopes[j], slopes[j + 1]) == 0) {
                    count++;
                }
                if (count >= 2) {
                    linesList.add(new LineSegment(origin, others[j + 1]));
                    break;
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return linesList.size();

    }

    // the line segments
    public LineSegment[] segments() {
        return linesList.toArray(new LineSegment[this.numberOfSegments()]);
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();

        System.out.println(n);
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            p.draw();
        }
        StdDraw.show();
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        System.out.println(collinear.numberOfSegments());


        for (LineSegment segment : collinear.segments()) {
            System.out.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
