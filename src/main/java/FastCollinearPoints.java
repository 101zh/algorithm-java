import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class FastCollinearPoints {

    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
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

        int lineSegmentCount = 0;
        LineSegment[] tempLineSegments = new LineSegment[points.length * points.length];

        for (int p = 0; p < points.length; p++) {
            Point pPoint = points[p];
            Point startPoint;
            Point endPoint;
            Arrays.sort(myPoints);
            Arrays.sort(myPoints, pPoint.slopeOrder());
            int count = 1;

            for (int q = 0; q < myPoints.length - 1; q++) {
                Point curPoint = myPoints[q];
                if (pPoint.slopeTo(curPoint) == pPoint.slopeTo(myPoints[q + 1])) {
                    if (pPoint.compareTo(curPoint) > 0) {
                        count = 1;
                        break;
                        // you may run into a situation where the slope to two points match, but won't
                        // actually form a line
                        // if pPoint is greater then it will just stop iterating through the list,
                        // possibley missing lines
                        // SO FIX IT
                    }
                    count++;
                } else {
                    if (count >= 3) {
                        tempLineSegments[lineSegmentCount] = new LineSegment(pPoint, myPoints[q]);
                        lineSegmentCount++;
                    }
                    count = 1;
                }
            }

            if (count >= 3) {
                tempLineSegments[lineSegmentCount] = new LineSegment(pPoint, myPoints[myPoints.length - 1]);
                lineSegmentCount++;
            }
        }

        // Copies over line segments
        lineSegments = new LineSegment[lineSegmentCount];
        for (int i = 0; i < lineSegmentCount; i++) {
            lineSegments[i] = tempLineSegments[i];
        }

    }

    private static LineSegment[] deepCopy(LineSegment[] arr) {
        LineSegment[] temp = new LineSegment[arr.length];

        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }

        return temp;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return deepCopy(lineSegments);
    }

    public static void main(String[] args) {
        Point point3 = new Point(30000, 8500);
        Point point2 = new Point(29550, 8500);
        Point point = new Point(23000, 8500);
        Point point4 = new Point(30950, 8500);

        Point[] points = { point3, point2, point, point4 };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);

        // for (int i = 0; i < points.length; i++) {
        // int randIndex = (int) (StdRandom.uniform() * (points.length - i)) + i;

        // Point tempItem = points[randIndex];
        // points[randIndex] = points[i];
        // points[i] = tempItem;

        // }
        System.out.println(Arrays.deepToString(points));

        System.out.println(Arrays.deepToString(fastCollinearPoints.segments()));
    }
}
