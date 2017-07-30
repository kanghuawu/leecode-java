import java.lang.IllegalArgumentException;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private final Point[] points;
    private final LineSegment[] segments;
    private int numberOfSegments;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new java.lang.IllegalArgumentException("Invalid input");
        this.points = points;
        segments = new LineSegment[points.length];
        numberOfSegments = 0;
        Arrays.sort(points);
        if (hasDuplicatesOrNull(points))
            throw new IllegalArgumentException("Found repeating points or null");
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {

                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
                            segments[numberOfSegments] = new LineSegment(points[i], points[l]);
                            numberOfSegments++;
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }
    
    // the line segments
    public LineSegment[] segments() {
        LineSegment[] res = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++) {
            res[i] = segments[i];
        }
        return res;
    }

    private boolean hasDuplicatesOrNull(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null || points[i].compareTo(points[i+1]) == 0)
                return true;
        }
        if (points[points.length - 1] == null)
            return true;
        return false;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}