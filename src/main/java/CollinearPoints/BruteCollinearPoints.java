package CollinearPoints;

import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        // Checks argument for null
        if (points == null)
            throw new IllegalArgumentException();

        // Checks array indices for null, otherwise copies to myPoints
        Point[] myPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            } else {
                myPoints[i] = points[i];
            }
        }

        // Check for Duplicate Points
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i != j && points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

        // Have to sort array b/c line has to start at smallest/largest point
        Arrays.sort(myPoints);

        int lineSegmentCount = 0;
        Point pA, pB, pC, pD;
        LineSegment[] tempLineSegments = new LineSegment[myPoints.length * myPoints.length];

        // Goes through every combination of points to find points with the same slope
        for (int a = 0; a < myPoints.length; a++) {
            for (int b = a + 1; b < myPoints.length; b++) {
                for (int c = b + 1; c < myPoints.length; c++) {
                    for (int d = c + 1; d < myPoints.length; d++) {
                        pA = myPoints[a];
                        pB = myPoints[b];
                        pC = myPoints[c];
                        pD = myPoints[d];

                        if ((pA.slopeTo(pB) == pA.slopeTo(pC)) && (pA.slopeTo(pC) == pA.slopeTo(pD))) {
                            tempLineSegments[lineSegmentCount] = new LineSegment(pA, pD);
                            lineSegmentCount++;
                        }
                    }
                }
            }
        }

        // Copies over line segments
        lineSegments = new LineSegment[lineSegmentCount];
        for (int i = 0; i < lineSegmentCount; i++) {
            lineSegments[i] = tempLineSegments[i];
        }

    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    private static LineSegment[] deepCopy(LineSegment[] arr) {
        LineSegment[] temp = new LineSegment[arr.length];

        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }

        return temp;
    }

    public LineSegment[] segments() {
        return deepCopy(lineSegments);
    }

    public static void main(String[] args) {
        Point point2 = new Point(1, 4);
        Point point3 = new Point(2, 3);
        Point point4 = new Point(3, 2);
        Point point5 = new Point(4, 1);

        Point[] points = { point3, point4, point2, point5 };

        BruteCollinearPoints a = new BruteCollinearPoints(points);

        System.out.println(Arrays.deepToString(a.segments()));
    }
}
