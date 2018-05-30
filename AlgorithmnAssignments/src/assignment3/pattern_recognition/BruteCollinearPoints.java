package assignment3.pattern_recognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final List<LineSegment> linesList;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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

        for (int p = 0; p < len - 3; p++) {
            for (int q = p + 1; q < len - 2; q++) {
                double slopeP2Q = copy[p].slopeTo(copy[q]);
                for (int r = q + 1; r < len - 1; r++) {
                    double slopeQ2R = copy[q].slopeTo(copy[r]);
                    if (Double.compare(slopeP2Q, slopeQ2R) != 0)
                        continue;
                    for (int s = r + 1; s < len; s++) {
                        double slopeR2S = copy[r].slopeTo(copy[s]);
                        if (Double.compare(slopeQ2R, slopeR2S) == 0) {
                            linesList.add(new LineSegment(copy[p], copy[s]));
                        }
                    }
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

